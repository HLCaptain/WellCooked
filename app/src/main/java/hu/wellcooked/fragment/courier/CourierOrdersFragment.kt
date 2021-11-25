package hu.wellcooked.fragment.courier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hu.wellcooked.R
import hu.wellcooked.databinding.FragmentCourierOrdersBinding

class CourierOrdersFragment : Fragment() {
    private lateinit var binding: FragmentCourierOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourierOrdersBinding.inflate(inflater, container, false)

        binding.btOrderInfo.setOnClickListener {
            findNavController().navigate(R.id.action_courierOrdersFragment_to_nav_graph_courier_order_info)
        }
        return binding.root
    }
}