package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName
import stenograffia.app.data.network.model.MaterialModelNetwork
import stenograffia.app.data.network.model.PaintModelNetwork

class ElementsMaterialResponse(
    @SerializedName("elements")
    val material: List<MaterialModelNetwork>
) {}