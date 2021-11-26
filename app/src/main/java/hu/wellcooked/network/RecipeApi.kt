package hu.wellcooked.network

import hu.wellcooked.model.RecipeDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
//    @GET("/data/2.5/weather")
//    fun getWeather(
//        @Query("q") cityName: String?,
//        @Query("units") units: String?,
//        @Query("appid") appId: String?
//    ): Call<WeatherData?>?

    @GET("/todo")
    fun getRandomRecipes(
        @Query("size") size: Int = 10
    ) : Call<List<RecipeDto>>
}