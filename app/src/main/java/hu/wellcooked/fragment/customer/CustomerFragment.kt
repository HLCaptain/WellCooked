package hu.wellcooked.fragment.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.wellcooked.databinding.FragmentCustomerBinding

class CustomerFragment : Fragment() {
    private lateinit var binding: FragmentCustomerBinding

    companion object {
        private var instance: CustomerFragment? = null
        fun getInstance(): CustomerFragment {
            if (instance == null) {
                instance = CustomerFragment()
            }
            return instance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}