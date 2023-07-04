package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName

data class ChangeLocationRequest (

    @SerializedName("material_id")
    val materialId: Int,

    @SerializedName("location")
    val location: String,

    @SerializedName("time_label")
    val timeLabel: Int
)
