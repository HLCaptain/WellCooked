package hu.wellcooked.fragment.customer

import Results
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import hu.wellcooked.R
import hu.wellcooked.databinding.FragmentRecipeInfoBinding
import hu.wellcooked.model.*
import hu.wellcooked.network.RecipeNetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.random.Random

class RecipeInfoFragment : Fragment() {
    private lateinit var binding: FragmentRecipeInfoBinding
    private var recipe: Results? = null
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
        if (args.recipe != null) {
            recipe = args.recipe!!
            initView()
        } else {
            loadRecipe(args.recipeId)
        }
    }

    private fun initView() {
        val instructions = StringBuilder()
        recipe?.instructions?.let {
            for (step in recipe?.instructions!!) {
                instructions.appendLine(step.display_text)
            }
        }

        val ingredients = StringBuilder()
        recipe?.sections?.let { sections ->
            for (section in sections) {
                section.components?.let { components ->
                    for (component in components) {
                        ingredients.appendLine(component.raw_text)
                    }
                }
            }
        }
        binding.apply {
            Glide
                .with(recipeImage)
                .load(recipe?.thumbnail_url)
                .centerCrop()
                .into(recipeImage)
            recipeImage.contentDescription = recipe?.thumbnail_alt_text ?: getString(R.string.unknown_variable)
            recipeTitle.text = recipe?.name
            recipeCredits.text = "Credits: " + recipe?.credits?.get(Random.nextInt(recipe?.credits?.size!!))?.name ?: getString(R.string.unknown_variable)
            recipeInstructions.text = instructions
            recipeIngredients.text = ingredients
            fabOrderRecipe.setOnClickListener {
                // TODO: make a factory for orders
                // TODO: refactor data hierarchy
                Firebase.auth.currentUser?.let { user ->
                    Firebase.firestore
                        .collection("orders")
                        .add(Order(
                            id = UUID.randomUUID().toString(),
                            customer = Customer(
                                id = user.uid,
                                name = user.displayName!!
                            ),
                            site = Site(
                                id = UUID.randomUUID().toString(),
                                latitude = 69.0,
                                longitude = 69.0
                            ),
                            orderDate = Date().toString(),
                            completionDate = "",
                            status = OrderStatus.ORDERED,
                            recipe = Recipe(
                                id = recipe?.id.toString(),
                                thumbnailUrl = recipe?.thumbnail_url ?: "",
                                name = recipe?.name!!
                            )
                        ))
                }
            }
        }
    }

    private fun loadRecipe(results: Results) {
        recipe = results
    }

    private fun loadRecipe(recipeId: Int) {
        RecipeNetworkManager.getRecipe(recipeId).enqueue(object :
            Callback<Results> {
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                Log.d(RecipeAdapter.TAG, "onResponse: " + response.code())
                if (response.isSuccessful) {
                    response.body()?.let {
                        loadRecipe(it)
                        initView()
                    }
                } else {
                    Toast.makeText(context, "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }                    }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }
        })
    }
}