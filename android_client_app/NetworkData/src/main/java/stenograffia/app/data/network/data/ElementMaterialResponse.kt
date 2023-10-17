package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName
import stenograffia.app.data.network.model.MaterialModelNetwork

class ElementMaterialResponse(
    @SerializedName("elements")
    val material: List<MaterialModelNetwork>
) {}