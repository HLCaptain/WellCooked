package hu.wellcooked.fragment.courier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.wellcooked.databinding.FragmentCourierNewOrdersBinding
import hu.wellcooked.fragment.customer.CustomerOrdersFragmentDirections
import hu.wellcooked.fragment.customer.OrderAdapter

class CourierNewOrdersFragment : Fragment() {
    private lateinit var binding: FragmentCourierNewOrdersBinding
    private lateinit var adapter: CourierNewOrdersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourierNewOrdersBinding.inflate(inflater, container, false)

        binding.courierNewOrdersRecycler.layoutManager = LinearLayoutManager(context)
        adapter = CourierNewOrdersAdapter()
        adapter.setOnOrderChangedListener {
            binding.courierNewOrdersRecycler.adapter = adapter
        }
        adapter.setOnOrderSelectedListener {
            val action = CourierNewOrdersFragmentDirections.actionCourierNewOrdersFragmentToNavGraphCourierOrderInfo(it)
            findNavController().navigate(action)
        }

        binding.courierNewOrdersRecycler.adapter = adapter

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "New Orders"
    }
}