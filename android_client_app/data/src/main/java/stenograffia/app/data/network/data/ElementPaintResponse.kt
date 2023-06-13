package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName
import stenograffia.app.data.network.model.PaintModelNetwork

class ElementPaintResponse(
    @SerializedName("elements")
    val paints: List<PaintModelNetwork>
) {}