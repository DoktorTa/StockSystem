package stenograffia.app.localdata.database.model

import androidx.room.ColumnInfo
import stenograffia.app.domain.model.PaintNamesTupleModel

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