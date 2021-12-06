package hu.wellcooked.fragment.courier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import hu.wellcooked.R
import hu.wellcooked.databinding.FragmentCourierOrderInfoBinding
import hu.wellcooked.fragment.customer.CustomerOrderInfoFragmentArgs
import hu.wellcooked.fragment.customer.CustomerOrderInfoFragmentDirections
import hu.wellcooked.model.Order

class CourierOrderInfoFragment : Fragment() {
    private lateinit var binding: FragmentCourierOrderInfoBinding
    private lateinit var order: Order
    private val args: CustomerOrderInfoFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().actionBar?.title = "Order Information"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourierOrderInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        order = args.order
        initView()
    }

    private fun initView() {
        binding.apply {
            Glide
                .with(orderRecipeImage)
                .load(order.recipe?.thumbnailUrl)
                .centerCrop()
                .into(orderRecipeImage)
            orderDate.text = "Date ordered: " + order.orderDate ?: getString(R.string.unknown_variable)
            orderInfoCustomer.text = "Customer: " + order.customer?.name ?: getString(R.string.unknown_variable)
            orderStatus.text = "Status: " + order.status.toString() ?: getString(R.string.unknown_variable)
            orderTitle.text = order.recipe?.name
            (requireActivity() as AppCompatActivity).supportActionBar?.title = order.recipe?.name
            fabShowRecipe.setOnClickListener {
                val action = CourierOrderInfoFragmentDirections.actionCourierOrderInfoFragmentToNavGraphRecipeInfo(null, order.recipe?.id?.toInt()!!)
                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Order Information"
    }
}