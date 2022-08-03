package APIEXAMPLES

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("71b7bbdd-87a7-4e41-99e3-7f56d1f40f7b")
    fun getName():Call<UserResponse>
}