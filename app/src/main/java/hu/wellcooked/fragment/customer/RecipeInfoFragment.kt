package hu.wellcooked.fragment.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.wellcooked.databinding.FragmentRecipeInfoBinding

class RecipeInfoFragment : Fragment() {
    private lateinit var binding: FragmentRecipeInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeInfoBinding.inflate(inflater, container, false)
        return binding.root
    }
}