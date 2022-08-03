package GoogleDriveApiTest

import APIEXAMPLES.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface Google_Interface {
    @Multipart
    @POST("files")
    fun sendFile(@Header("Authorization") token: String,@Part file: MultipartBody.Part,@Part("data") filename: RequestBody): Call<DriveUploadResponse>
}