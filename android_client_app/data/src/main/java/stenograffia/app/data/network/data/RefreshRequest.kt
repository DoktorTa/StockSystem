package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName

data class RefreshRequest (
    @SerializedName("refresh_token")
    var refreshToken: String,
)