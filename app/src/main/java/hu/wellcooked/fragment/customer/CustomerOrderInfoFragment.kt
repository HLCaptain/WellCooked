package hu.wellcooked.fragment.customer

import Results
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import hu.wellcooked.R
import hu.wellcooked.databinding.FragmentCustomerOrderInfoBinding
import hu.wellcooked.model.Order
import hu.wellcooked.network.RecipeNetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerOrderInfoFragment : Fragment() {
    private lateinit var binding: FragmentCustomerOrderInfoBinding
    private lateinit var order: Order
    private val args: CustomerOrderInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerOrderInfoBinding.inflate(inflater, container, false)
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
            fabShowRecipe.setOnClickListener {
                val action = CustomerOrderInfoFragmentDirections.actionCustomerOrderInfoFragmentToNavGraphRecipeInfo(null, order.recipe?.id?.toInt()!!)
                findNavController().navigate(action)
            }
        }
    }
}