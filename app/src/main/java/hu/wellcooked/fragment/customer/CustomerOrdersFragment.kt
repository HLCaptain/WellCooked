package hu.wellcooked.fragment.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.wellcooked.databinding.FragmentCustomerOrdersBinding

class CustomerOrdersFragment : Fragment() {
    private lateinit var binding: FragmentCustomerOrdersBinding
    private lateinit var adapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerOrdersBinding.inflate(inflater, container, false)

        binding.customerOrdersRecycler.layoutManager = LinearLayoutManager(context)
        adapter = OrderAdapter()
        // TODO: ini recyclerview
        adapter.setOnOrderChangedListener {
            binding.customerOrdersRecycler.adapter = adapter
        }
        adapter.setOnOrderSelectedListener {
            val action = CustomerOrdersFragmentDirections.actionCustomerOrdersFragment2ToCustomerOrderInfoFragment(it)
            findNavController().navigate(action)
        }

        binding.customerOrdersRecycler.adapter = adapter

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Orders"
    }
}