package hu.wellcooked.network

import RecipesListResult
import hu.wellcooked.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import kotlin.random.Random

interface RecipeApi {
    @GET("/recipes/list")
    fun getRandomRecipes(
        @Query("size") size: Int = 10,
        @Query("tags") tags: Collection<String> = emptyList(),
        @Query("from") from: Int = Random.nextInt(1000),
        @Header("x-rapidapi-key") apiKey: String = BuildConfig.RAPID_API_KEY
    ): Call<RecipesListResult>
}