package stenograffia.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import stenograffia.app.data.database.typeconverters.PaintTypeConverter
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.TypePaint

@Entity
@TypeConverters(PaintTypeConverter::class)
data class PaintEntity(
    @PrimaryKey val id: Int,
    val type: String,
    val nameCreator: String,
    val nameLine: String,
    val codePaint: String,
    val nameColor: String,
    val descriptionColor: String,
    val color: Int,
    val quantityInStorage: Int,
    val placesOfPossibleAvailability: List<Int>,
    val similarColors: List<List<Int>>,
    val possibleToBuy: Boolean
)

fun PaintEntity?.toPaintModel(): PaintModel?{
    if (this == null) return null
    return PaintModel(
        id = this.id,
        type = TypePaint.createByString(this.type),
        nameCreator = this.nameCreator,
        nameLine = this.nameLine,
        codePaint = this.codePaint,
        nameColor = this.nameColor,
        descriptionColor = this.descriptionColor,
        color = this.color,
        quantityInStorage = this.quantityInStorage,
        placesOfPossibleAvailability = this.placesOfPossibleAvailability,
        similarColors = this.similarColors,
        possibleToBuy = this.possibleToBuy
    )
}

fun PaintModel?.fromPaintEntity(): PaintEntity?{
    if (this == null) return null
    return PaintEntity(
        id = this.id,
        type = this.type.name,
        nameCreator = this.nameCreator,
        nameLine = this.nameLine,
        codePaint = this.codePaint,
        nameColor = this.nameColor,
        descriptionColor = this.descriptionColor,
        color = this.color,
        quantityInStorage = this.quantityInStorage,
        placesOfPossibleAvailability = this.placesOfPossibleAvailability,
        similarColors = this.similarColors,
        possibleToBuy = this.possibleToBuy
    )
}
