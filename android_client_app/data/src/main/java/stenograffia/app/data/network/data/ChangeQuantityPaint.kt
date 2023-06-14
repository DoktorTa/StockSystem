package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName

data class ChangeQuantityPaint(

    @SerializedName("paint_id")
    var paintId: Int,

    @SerializedName("diff_quantity")
    var diffQuantity: Int,

    @SerializedName("time_label")
    var timeLabel: Int

) {}