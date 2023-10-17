package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName

data class ChangeMaterialRequest (

    @SerializedName("material_id")
    val materialId: Int,

    @SerializedName("location")
    val location: String,

    @SerializedName("diff_quantity")
    var diffQuantity: Int,

    @SerializedName("time_label")
    val timeLabel: Int
)
