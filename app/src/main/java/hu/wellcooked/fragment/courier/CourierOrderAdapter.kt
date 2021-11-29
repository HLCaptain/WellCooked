package hu.wellcooked.fragment.courier

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import hu.wellcooked.R
import hu.wellcooked.databinding.FragmentCourierOrderItemBinding
import hu.wellcooked.databinding.FragmentCustomerOrderItemBinding
import hu.wellcooked.model.Order
import java.lang.StringBuilder

class CourierOrderAdapter : RecyclerView.Adapter<CourierOrderAdapter.CourierOrderViewHolder>() {
    companion object {
        const val TAG = "hu.wellcooked.fragment.courier.CourierOrderAdapter"
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourierOrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_customer_order_item, parent, false)
        return CourierOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourierOrderAdapter.CourierOrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int = orders.size

    private fun initOrdersListener() {
        if (user != null) {
            db.collection("users")
                .document(user.uid)
                .collection("takenOrders")
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                    }
                    if (snapshot != null) {
                        for (dc in snapshot.documentChanges) {
                            val order = dc.document.toObject(Order::class.java)
                            when (dc.type) {
                                DocumentChange.Type.ADDED -> {
                                    orders.add(order)
                                    notifyItemInserted(orders.indexOf(order))
                                }
                                DocumentChange.Type.MODIFIED -> {
                                    val modifiedOrder = orders.firstOrNull {
                                        return@firstOrNull it.id == order.id
                                    }
                                    modifiedOrder?.let {
                                        val index = orders.indexOf(it)
                                        orders[index] = order
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
        } else {
            Log.i(TAG, "Firebase user is not signed in.")
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

    inner class CourierOrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FragmentCourierOrderItemBinding.bind(view)
        private var order: Order? = null
        init {
            binding.courierOrderCard.setOnClickListener {
                order?.let { onOrderSelectedListener?.onOrderSelected(it) }
            }
        }

        fun bind(order: Order) {
            this.order = order
            binding.apply {
                Glide
                    .with(itemView)
                    .load(order.recipe?.thumbnailUrl)
                    .centerCrop()
                    .into(courierOrderRecipeImage)
                courierOrderTitle.text = order.status?.name
                val desc = StringBuilder()
                desc.appendLine("Name: ${order.customer?.name}")
                desc.appendLine("Ordered: ${order.orderDate}")
                desc.appendLine("Recipe: ${order.recipe?.name}")
                courierOrderDesc.text = desc
            }
        }
    }
}