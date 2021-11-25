package hu.wellcooked.fragment.courier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.wellcooked.databinding.FragmentCourierHomeBinding

class CourierHomeFragment : Fragment() {
    private lateinit var binding: FragmentCourierHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourierHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}