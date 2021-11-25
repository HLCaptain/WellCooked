package hu.wellcooked.fragment.courier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hu.wellcooked.databinding.FragmentCourierOrderInfoBinding

class CourierOrderInfoFragment : Fragment() {
    private lateinit var binding: FragmentCourierOrderInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourierOrderInfoBinding.inflate(inflater, container, false)

        binding.btGoBack.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }
}