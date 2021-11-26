package hu.wellcooked.fragment.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.wellcooked.R
import hu.wellcooked.model.Recipe

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    private val recipes = mutableListOf<Recipe>()
    init {
        refreshRecipes()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_recipe_info, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun refreshRecipes() {
        recipes.clear()

    }

    class RecipeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    }
}