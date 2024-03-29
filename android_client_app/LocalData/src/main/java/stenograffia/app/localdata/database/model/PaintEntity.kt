package stenograffia.app.localdata.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import stenograffia.app.localdata.database.typeconverters.PaintTypeConverter
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintType

@Entity
@TypeConverters(PaintTypeConverter::class)
data class PaintEntity(
    @PrimaryKey val id: Int,
    val type: String,
    val nameCreator: String,
    val timeLabel: Int,
    val nameLine: String,
    val codePaint: String,
    val nameColor: String,
    val descriptionColor: String,
    val color: Int,
    val quantityInStorage: Int,
    val similarColors: List<List<Int>>,
    val possibleToBuy: Boolean
)

fun PaintEntity?.toPaintModel(): PaintModel?{
    if (this == null) return null
    return PaintModel(
        id = this.id,
        type = PaintType.getPaintTypeByName(this.type),
        timeLabel = this.timeLabel,
        nameCreator = this.nameCreator,
        nameLine = this.nameLine,
        codePaint = this.codePaint,
        nameColor = this.nameColor,
        descriptionColor = this.descriptionColor,
        color = this.color,
        quantityInStorage = this.quantityInStorage,
        similarColors = this.similarColors,
        possibleToBuy = this.possibleToBuy
    )
}

fun PaintModel?.fromPaintEntity(): PaintEntity?{
    if (this == null) return null
    return PaintEntity(
        id = this.id,
        type = this.type.name,
        timeLabel = this.timeLabel,
        nameCreator = this.nameCreator,
        nameLine = this.nameLine,
        codePaint = this.codePaint,
        nameColor = this.nameColor,
        descriptionColor = this.descriptionColor,
        color = this.color,
        quantityInStorage = this.quantityInStorage,
        similarColors = this.similarColors,
        possibleToBuy = this.possibleToBuy
    )
}
