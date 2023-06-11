package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName
import stenograffia.app.data.network.model.PaintModelNetwork

data class SinglePaintResponse(
    @SerializedName("paint")
    var paint: PaintModelNetwork
) {}
