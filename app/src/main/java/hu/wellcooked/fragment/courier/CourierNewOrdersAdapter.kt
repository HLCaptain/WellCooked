package hu.wellcooked.fragment.courier

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import hu.wellcooked.R
import hu.wellcooked.databinding.FragmentCourierNewOrderItemBinding
import hu.wellcooked.databinding.FragmentCourierOrderItemBinding
import hu.wellcooked.model.Order
import hu.wellcooked.model.UnassignedOrder
import hu.wellcooked.model.User
import java.lang.StringBuilder

class CourierNewOrdersAdapter : RecyclerView.Adapter<CourierNewOrdersAdapter.CourierNewOrdersViewHolder>() {
    companion object {
        const val TAG = "hu.wellcooked.fragment.courier.CourierNewOrdersAdapter"
    }
    val orders = mutableListOf<Order>()
    private var onOrdersChangedListener: OnOrdersChangedListener? = null
    private var onOrderSelectedListener: OnOrderSelectedListener? = null
    private val db = Firebase.firestore
    private val user = Firebase.auth.currentUser

    init {
        initOrdersListener()
    }

    interface OnOrdersChangedListener {
        fun onOrdersChanged(orders: List<Order>)
    }
    interface OnOrderSelectedListener {
        fun onOrderSelected(order: Order)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourierNewOrdersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_courier_new_order_item, parent, false)
        return CourierNewOrdersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourierNewOrdersViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size

    private fun initOrdersListener() {
        if (user != null) {
            db.collection("unassignedOrders")
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                    }
                    if (snapshot != null) {
                        for (dc in snapshot.documentChanges) {
                            val unassignedOrder = dc.document.toObject(UnassignedOrder::class.java)
                            db.collection("users")
                                .document(unassignedOrder.userId)
                                .collection("orders")
                                .document(unassignedOrder.orderId)
                                .get()
                                .addOnSuccessListener {
                                    val order = it.toObject(Order::class.java)
                                    when (dc.type) {
                                        DocumentChange.Type.ADDED -> {
                                            order?.let { orders.add(order) }
                                            notifyItemInserted(orders.indexOf(order))
                                        }
                                        DocumentChange.Type.MODIFIED -> {
                                            val modifiedOrder = orders.firstOrNull { first ->
                                                return@firstOrNull first.id == order!!.id
                                            }
                                            modifiedOrder?.let { mOrder ->
                                                val index = orders.indexOf(mOrder)
                                                orders[index] = order!!
                                                notifyItemChanged(index)
                                            }
                                        }
                                        DocumentChange.Type.REMOVED -> {
                                            val index = orders.indexOf(order)
                                            orders.remove(order)
                                            notifyItemRemoved(index)
                                        }
                                    }
                                }

                        }
                    }
                }
        } else {
            Log.i(TAG, "Firebase user is not signed in.")
        }
    }

    fun assignOrder(order: Order) {
        if (user != null) {
            order.courier = User(
                name = user.displayName!!,
                id = user.uid
            )
            db.collection("users")
                .document(user.uid)
                .collection("takenOrders")
                .document(order.id)
                .set(order)
                .addOnSuccessListener {
                    db.collection("users")
                        .document(order.customer!!.id)
                        .collection("orders")
                        .document(order.id)
                        .update("courier", order.courier)
                    db.collection("unassignedOrders")
                        .document(order.id)
                        .delete()
                        .addOnSuccessListener {
                            Log.i(TAG, "Deleted order: id=${order.id}")
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "Failed to delete order: id=${order.id}")
                        }
                }
        }
    }

    fun setOnOrderChangedListener(listener: (orders: List<Order>) -> Unit) {
        onOrdersChangedListener = object : OnOrdersChangedListener {
            override fun onOrdersChanged(orders: List<Order>) {
                listener(orders)
            }
        }
    }

    fun setOnOrderSelectedListener(listener: (order: Order) -> Unit) {
        onOrderSelectedListener = object : OnOrderSelectedListener {
            override fun onOrderSelected(order: Order) {
                listener(order)
            }
        }
    }

    inner class CourierNewOrdersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FragmentCourierNewOrderItemBinding.bind(view)
        private var order: Order? = null
        init {
            binding.courierNewOrderCard.setOnClickListener {
                order?.let { onOrderSelectedListener?.onOrderSelected(it) }
            }
        }

        fun bind(order: Order) {
            this.order = order
            binding.apply {
                courierNewOrderAddButton.setOnClickListener {
                    assignOrder(order)
                }
                courierNewOrderTitle.text = order.status?.name
                val desc = StringBuilder()
                desc.appendLine("Name: ${order.customer?.name}")
                desc.appendLine("Ordered: ${order.orderDate}")
                desc.appendLine("Recipe: ${order.recipe?.name}")
                courierNewOrderDesc.text = desc
            }
        }
    }
}