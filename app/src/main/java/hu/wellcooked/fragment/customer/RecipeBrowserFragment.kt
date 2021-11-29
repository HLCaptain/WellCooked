package hu.wellcooked.fragment.customer

import Results
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.wellcooked.databinding.FragmentRecipeBrowserBinding

// todo restore state with viewmodel
class RecipeBrowserFragment : Fragment() {
    private lateinit var binding: FragmentRecipeBrowserBinding
    private lateinit var adapter: RecipeAdapter
    private var state: List<Results>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        state = savedInstanceState?.getParcelableArrayList("recipes")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBrowserBinding.inflate(inflater, container, false)

        binding.recipesRecycler.layoutManager = LinearLayoutManager(context)
        adapter = RecipeAdapter()

        adapter.setOnRecipesChangedListener {
            binding.recipeBrowserSwipeRefreshLayout.isRefreshing = false
            binding.recipesRecycler.adapter = adapter
        }
        adapter.setOnRecipeSelectedListener {
            val action = RecipeBrowserFragmentDirections.actionRecipeBrowserFragmentToNavGraphRecipeInfo(it)
            findNavController().navigate(action)
        }
        binding.recipesRecycler.adapter = adapter
        binding.recipeBrowserSwipeRefreshLayout.setOnRefreshListener {
            refreshRecipes()
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("recipes", ArrayList(adapter.recipes))
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        state?.let { adapter.refreshList(it) }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        state = adapter.recipes
    }

    private fun refreshRecipes() {
        context?.let { adapter.refreshRecipes() }
    }
}