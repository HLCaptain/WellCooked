package hu.wellcooked.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RecipeNetworkManager {
    private val retrofit: Retrofit
    private val recipeApi: RecipeApi

    private const val SERVICE_URL = ""
    private const val API_KEY = ""

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        recipeApi = retrofit.create(RecipeApi::class.java)
    }

}