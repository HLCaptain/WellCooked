package hu.wellcooked.fragment.courier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.wellcooked.databinding.FragmentCourierBinding

class CourierFragment : Fragment() {
    private lateinit var binding: FragmentCourierBinding

    companion object {
        private var instance: CourierFragment? = null
        fun getInstance(): CourierFragment {
            if (instance == null) {
                instance = CourierFragment()
            }
            return instance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourierBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}