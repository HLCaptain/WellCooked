package hu.wellcooked.fragment.courier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import hu.wellcooked.databinding.FragmentCourierBinding

class CourierFragment : Fragment() {
    private lateinit var binding: FragmentCourierBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourierBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.bottomNavigationCourier.setupWithNavController(
            binding.courierNavHostFragment.findNavController()
        )
    }
}