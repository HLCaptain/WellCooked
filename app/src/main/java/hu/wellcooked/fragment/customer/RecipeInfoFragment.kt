package hu.wellcooked.fragment.customer

import Results
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import hu.wellcooked.R
import hu.wellcooked.databinding.FragmentRecipeInfoBinding
import kotlin.random.Random

class RecipeInfoFragment : Fragment() {
    private lateinit var binding: FragmentRecipeInfoBinding
    private lateinit var recipe: Results
    private val args: RecipeInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipe = args.recipe
        Glide
            .with(view)
            .load(recipe.thumbnail_url)
            .centerCrop()
            .into(binding.recipeImage)
        binding.recipeImage.contentDescription = recipe.thumbnail_alt_text
        binding.recipeTitle.text = recipe.name
        binding.recipeCredits.text = "Credits: " + recipe.credits?.get(Random.nextInt(recipe.credits?.size!!))?.name ?: getString(R.string.unknown_variable)

        val instructions = StringBuilder()
        recipe.instructions?.let {
            for (step in recipe.instructions!!) {
                instructions.appendLine(step.display_text)
            }
        }
        binding.recipeInstructions.text = instructions

        val ingredients = StringBuilder()
        recipe.sections?.let { sections ->
            for (section in sections) {
                section.components?.let { components ->
                    for (component in components) {
                        ingredients.appendLine(component.raw_text)
                    }
                }
            }
        }

        binding.recipeIngredients.text = ingredients
    }
}