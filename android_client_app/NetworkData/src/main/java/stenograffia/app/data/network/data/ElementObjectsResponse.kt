package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName
import stenograffia.app.data.network.model.ObjectModelNetwork

data class ElementObjectsResponse(
    @SerializedName("elements")
    val objects: List<ObjectModelNetwork>
)
