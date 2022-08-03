package GoogleDriveApiTest

import APIEXAMPLES.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GoogleClient {
    var serverUrl="https://www.googleapis.com/upload/drive/v3/"

    private val retrofitClient: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(serverUrl).addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterFace: Google_Interface by lazy {
        retrofitClient.build().create(Google_Interface::class.java)
    }
}