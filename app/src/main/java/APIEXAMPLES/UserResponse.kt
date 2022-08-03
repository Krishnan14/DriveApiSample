package APIEXAMPLES

import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("name")
    var name: String? =""

    @SerializedName("age")
    var age: String? =""
}