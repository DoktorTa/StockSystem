package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName

data class ChangeQuantityPaint(

    @SerializedName("id_paint")
    var paintId: Int,

    @SerializedName("diff_quantity")
    var diffQuantity: Int

) {}