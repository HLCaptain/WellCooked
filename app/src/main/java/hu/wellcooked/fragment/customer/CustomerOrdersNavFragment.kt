package hu.wellcooked.fragment.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import hu.wellcooked.MainActivity
import hu.wellcooked.databinding.FragmentCustomerOrdersNavBinding

class CustomerOrdersNavFragment : Fragment() {
    private lateinit var binding: FragmentCustomerOrdersNavBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerOrdersNavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val activity = parentFragment?.requireActivity() as MainActivity
        activity.setNavController(binding.customerOrdersNavHostFragment.findNavController())
    }
}