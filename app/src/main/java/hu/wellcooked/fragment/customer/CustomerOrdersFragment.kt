package hu.wellcooked.fragment.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.wellcooked.databinding.FragmentCustomerOrdersBinding

class CustomerOrdersFragment : Fragment() {
    private lateinit var binding: FragmentCustomerOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }
}