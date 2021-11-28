package hu.wellcooked.network

import RecipesListResult
import hu.wellcooked.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

object RecipeNetworkManager {
    private val retrofit: Retrofit
    private val recipeApi: RecipeApi

    private const val SERVICE_URL = "https://tasty.p.rapidapi.com"
    private const val API_KEY = BuildConfig.RAPID_API_KEY

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        recipeApi = retrofit.create(RecipeApi::class.java)
    }

    fun getRandomRecipes(
        size: Int = 10,
        tags: Collection<String> = emptyList()
    ): Call<RecipesListResult> = recipeApi.getRandomRecipes(size, tags, Random.nextInt(1000), API_KEY)
}