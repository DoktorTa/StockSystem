package stenograffia.app.data.network.model

import stenograffia.app.domain.model.MaterialModel

class MaterialModelNetwork(
    val material_id: Int,
    val material_type: String,
    val time_label: Int,
    val description: String,
    val unique: Boolean,
    val location: String,
    val quantity_in_storage: Int
) {}

fun MaterialModelNetwork.toMaterialModel(): MaterialModel {
    return MaterialModel(
        id = material_id,
        type = material_type,
        timeLabel = time_label,
        description = description,
        unique = unique,
        location = location,
        quantityInStorage = quantity_in_storage
    )
}