package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("refresh_token")
    var refreshToken: String,

    @SerializedName("user_name")
    var userName: String,
    @SerializedName("user_group")
    var userGroup: String
)