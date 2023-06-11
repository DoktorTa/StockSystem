package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName

data class UpdateQuantityRequest(

    @SerializedName("id_paint")
    var paintId: Int,

    @SerializedName("diff_quantity")
    var diffQuantity: Int

) {}