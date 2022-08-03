package GoogleDriveApiTest

//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.api.client.extensions.android.http.AndroidHttp
//import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
//import com.google.api.client.http.FileContent
//import com.google.api.client.json.jackson2.JacksonFactory
//import com.google.api.services.drive.Drive
//import com.google.api.services.drive.DriveRequest
//import com.google.api.services.drive.DriveScopes
//import com.google.api.services.drive.model.File

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.driveapisample.R
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


class GoogleActivity : AppCompatActivity() {
lateinit var editImageView: ImageView
lateinit var editButton: Button
    val pickImage = 100
    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        title = "KotlinApp"
        editImageView = findViewById(R.id.imageView)
        editButton = findViewById(R.id.buttonLoadPicture)
        editButton.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            println("getFile "+getFile(applicationContext,imageUri!!).toString())
            uploadImage(getFile(applicationContext,imageUri!!)!!)
            editImageView.setImageURI(imageUri)
        }
    }

    @Throws(IOException::class)
    fun getFile(context: Context, uri: Uri): File? {
        val destinationFilename = File(
            context.getFilesDir().getPath() + File.separatorChar.toString() + queryName(
                context,
                uri
            )
        )
        try {
            context.getContentResolver().openInputStream(uri).use { ins ->
                createFileFromStream(
                    ins!!,
                    destinationFilename
                )
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return destinationFilename
    }

    fun createFileFromStream(ins: InputStream, destination: File?) {
        try {
            FileOutputStream(destination).use { os ->
                val buffer = ByteArray(4096)
                var length: Int
                while (ins.read(buffer).also { length = it } > 0) {
                    os.write(buffer, 0, length)
                }
                os.flush()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun queryName(context: Context, uri: Uri): String {
        val returnCursor: Cursor = context.getContentResolver().query(uri, null, null, null, null)!!
        val nameIndex: Int = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name: String = returnCursor.getString(nameIndex)
        returnCursor.close()
        return name
    }

    private fun uploadImage(file:File) {

        val requestFile: RequestBody = RequestBody.create(
            "image/jpg".toMediaType(),
            file
        )


        val multipartImage =
            MultipartBody.Part.createFormData("image", file.getName(), requestFile)

        val fileName = RequestBody.create("text/plain".toMediaTypeOrNull(), "{\"name\":\"hellodummy.jpg\"}")

        val apiInterface: Google_Interface = GoogleClient().apiInterFace
        val apiCall =
            apiInterface.sendFile("Bearer ya29.A0AVA9y1s1mIotpOqisdPWcj8KjMl4zoNSvD97F0OooQn2wZub2btCD04swnPR2472RihR7AT08bhs6RBo01KovNW1IZj2_GJwssFsIRSXffzfzxNIpIJ4F789d5SLEJApRP0fJc_3qD1oRz_NpLalBo8c4hyxYUNnWUtBVEFTQVRBU0ZRRTY1ZHI4bTJ1V011RW9kVWhUVkhiWmZBdmNtQQ0163",multipartImage,fileName)
        apiCall.enqueue(object : Callback<DriveUploadResponse> {
            override fun onResponse(
                call: Call<DriveUploadResponse>,
                response: Response<DriveUploadResponse>
            ) {
                println("serverResponse " + Gson().toJson(response.body()))
            }

            override fun onFailure(call: Call<DriveUploadResponse>, t: Throwable) {
                println("serverResponse " + t.message)
            }


        })
    }

}

