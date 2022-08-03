package APIEXAMPLES

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient() {
    var serverUrl="https://mocki.io/v1/"

    private val retrofitClient: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(serverUrl).addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterFace: ApiInterface by lazy {
        retrofitClient.build().create(ApiInterface::class.java)
    }
}