package hu.wellcooked.fragment.customer

import RecipesListResult
import Results
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.wellcooked.R
import hu.wellcooked.databinding.FragmentRecipeInfoBinding
import hu.wellcooked.databinding.FragmentRecipeItemBinding
import hu.wellcooked.model.Recipe
import hu.wellcooked.network.RecipeNetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    companion object {
        const val TAG = "hu.wellcooked.fragment.customer.RecipeAdapter"
    }
    val recipes = mutableListOf<Results>()
    private var onRecipesChangedListener: OnRecipesChangedListener? = null
    private var onRecipeSelectedListener: OnRecipeSelectedListener? = null

    interface OnRecipesChangedListener {
        fun onRecipesChanged(recipes: List<Results>)
    }
    interface OnRecipeSelectedListener {
        fun onRecipeSelected(recipe: Results)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    fun refreshRecipes(context: Context? = null) {
        RecipeNetworkManager.getRandomRecipes().enqueue(object : Callback<RecipesListResult> {
            override fun onResponse(
                call: Call<RecipesListResult>,
                response: Response<RecipesListResult>
            ) {
                Log.d(TAG, "onResponse: " + response.code())
                if (response.isSuccessful) {
                    refreshList(response.body())
                } else {
                    Toast.makeText(context, "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(
                call: Call<RecipesListResult>,
                throwable: Throwable
            ) {
                throwable.printStackTrace()
                context?.let { Toast.makeText(it, "Network request error occured, check LOG", Toast.LENGTH_LONG).show() }
            }
        })
    }

    private fun refreshList(recipesListResult: RecipesListResult?) {
        recipesListResult?.results?.let {
            refreshList(it)
        }
    }

    fun refreshList(recipes: List<Results>) {
        val oldSize = recipes.size
        this.recipes.clear()
        notifyItemRangeRemoved(0, oldSize)
        this.recipes.addAll(recipes)
        notifyItemRangeInserted(0, this.recipes.size)
        onRecipesChangedListener?.onRecipesChanged(this.recipes.toList())
    }

    fun setOnRecipesChangedListener(listener: (recipes: List<Results>) -> Unit) {
        onRecipesChangedListener = object : OnRecipesChangedListener {
            override fun onRecipesChanged(recipes: List<Results>) {
                listener(recipes)
            }
        }
    }

    fun setOnRecipeSelectedListener(listener: (recipe: Results) -> Unit) {
        onRecipeSelectedListener = object : OnRecipeSelectedListener {
            override fun onRecipeSelected(recipe: Results) {
                listener(recipe)
            }
        }
    }

    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FragmentRecipeItemBinding.bind(view)
        private var recipe: Results? = null
        init {
            binding.recipeCard.setOnClickListener {
                recipe?.let { onRecipeSelectedListener?.onRecipeSelected(it) }
            }
        }

        fun bind(recipe: Results) {
            this.recipe = recipe

            binding.apply {
                Glide
                    .with(itemView)
                    .load(recipe.thumbnail_url)
                    .centerCrop()
                    .into(binding.recipeImage)
                binding.recipeImage.contentDescription = recipe.thumbnail_alt_text
                binding.recipeTitle.text = recipe.name
                binding.recipeTotalTime.text = "Time: " + recipe.total_time_tier?.display_tier ?: "Unknown"
                binding.recipeServings.text = "Yield: " + recipe.yields ?: "Unknown"
                binding.recipeCredits.text = "Credits: " + recipe.credits?.get(Random.nextInt(recipe.credits.size))?.name ?: "Unknown"
            }
        }
    }
}