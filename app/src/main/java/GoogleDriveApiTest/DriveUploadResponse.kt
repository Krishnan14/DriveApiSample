package GoogleDriveApiTest

import com.google.gson.annotations.SerializedName

class DriveUploadResponse {
    @SerializedName("kind")
    var kind: String? =""

    @SerializedName("id")
    var id: String? =""

    @SerializedName("name")
    var name: String? =""

    @SerializedName("mimeType")
    var mimeType: String? =""
}