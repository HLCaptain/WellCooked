package hu.wellcooked.fragment.courier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import hu.wellcooked.MainActivity
import hu.wellcooked.databinding.FragmentCourierHomeNavBinding

class CourierHomeNavFragment : Fragment() {
    private lateinit var binding: FragmentCourierHomeNavBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourierHomeNavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val activity = parentFragment?.requireActivity() as MainActivity
        activity.setNavController(binding.courierHomeNavHostFragment.findNavController())
    }
}