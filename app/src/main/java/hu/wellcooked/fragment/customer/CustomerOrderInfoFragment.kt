package hu.wellcooked.fragment.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.wellcooked.databinding.FragmentCustomerOrderInfoBinding

class CustomerOrderInfoFragment : Fragment() {
    private lateinit var binding: FragmentCustomerOrderInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerOrderInfoBinding.inflate(inflater, container, false)
        return binding.root
    }
}