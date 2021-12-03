package hu.wellcooked.fragment.courier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.wellcooked.R
import hu.wellcooked.databinding.FragmentCourierOrdersBinding

class CourierOrdersFragment : Fragment() {
    private lateinit var binding: FragmentCourierOrdersBinding
    private lateinit var adapter: CourierOrdersAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourierOrdersBinding.inflate(inflater, container, false)

        binding.courierOrdersRecycler.layoutManager = LinearLayoutManager(context)
        adapter = CourierOrdersAdapter()
        // TODO: ini recyclerview
        adapter.setOnOrderChangedListener {
            binding.courierOrdersRecycler.adapter = adapter
        }
        adapter.setOnOrderSelectedListener {
            val action = CourierOrdersFragmentDirections.actionCourierOrdersFragmentToNavGraphCourierOrderInfo(it)
            findNavController().navigate(action)
        }

        binding.courierOrdersRecycler.adapter = adapter

        return binding.root
    }
}