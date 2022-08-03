package APIEXAMPLES

import com.google.gson.annotations.SerializedName

class ResultResponse {
    @SerializedName("name")
    var responseName: String? =""

    @SerializedName("realname")
    var realname: String? =""

    @SerializedName("team")
    var team: String? =""
}