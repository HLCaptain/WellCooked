package hu.wellcooked.fragment.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.wellcooked.databinding.FragmentRecipeBrowserBinding

class RecipeBrowserFragment : Fragment() {
    private lateinit var binding: FragmentRecipeBrowserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBrowserBinding.inflate(inflater, container, false)
        return binding.root
    }
}