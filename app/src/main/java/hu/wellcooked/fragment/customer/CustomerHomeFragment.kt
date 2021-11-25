package hu.wellcooked.fragment.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.wellcooked.databinding.FragmentCustomerHomeBinding

class CustomerHomeFragment : Fragment() {
    private lateinit var binding: FragmentCustomerHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}