package stenograffia.app.data.database

import androidx.room.ColumnInfo
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.model.TypePaint

data class PaintNamesTuple(
    @ColumnInfo(name = "nameCreator") val nameCreator: String,
    @ColumnInfo(name = "nameLine") val nameLine: String
)

fun PaintNamesTuple.toPaintNamesTupleModel(): PaintNamesTupleModel{
    return PaintNamesTupleModel(
        nameCreator = this.nameCreator,
        nameLine = this.nameLine
    )
}