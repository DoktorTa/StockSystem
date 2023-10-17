package stenograffia.app.localdata.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import stenograffia.app.domain.model.MaterialModel

@Entity
data class MaterialEntity(
    @PrimaryKey val id: Int,
    val type: String,
    val timeLabel: Int,
    val description: String,
    val unique: Boolean,
    val location: String,
    val quantityInStorage: Int
){}

fun MaterialModel.fromMaterialEntity(): MaterialEntity {
    return MaterialEntity(
        id = id,
        type = type,
        timeLabel = timeLabel,
        description = description,
        unique = unique,
        location = location,
        quantityInStorage = quantityInStorage
    )
}

fun MaterialEntity.toMaterialModel(): MaterialModel {
    return MaterialModel(
        id = id,
        type = type,
        timeLabel = timeLabel,
        description = description,
        unique = unique,
        location = location,
        quantityInStorage = quantityInStorage
    )
}