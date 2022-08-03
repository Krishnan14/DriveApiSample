package APIEXAMPLES

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.driveapisample.R
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getApiList()
       /* sendGet();
        return;

        try {
            val url = URL("https://reqres.in/api/users/2")
            val connection = url.openConnection()
            val a = connection.getInputStream()
            val b = InputStreamReader(a)
            BufferedReader(b).use { inp ->
                var line: String?
                while (inp.readLine().also { line = it } != null) {
                    println(line)
                }
            }
        } catch (e: Exception) {
            val handler = null
            handler
            print(e);
        } finally {
            val optional = null
           // optional finally block
        }*/
    }

    private fun getApiList(){
        val apiInterface: ApiInterface = ApiClient().apiInterFace
        val apiCall=apiInterface.getName()
        apiCall.enqueue(object : retrofit2.Callback<UserResponse>{
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                println("serverResponse "+ Gson().toJson(response.body()))
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                println("serverResponse "+t.message)}


        })
    }

  /*  @RequiresApi(Build.VERSION_CODES.N)
    fun sendGet() {
        val url = URL("http://www.google.com/")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET

            println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

            inputStream.bufferedReader().use {
                print(it)
                it.lines().forEach { line ->
                    println(line) }
            }
        }
    }*/
}