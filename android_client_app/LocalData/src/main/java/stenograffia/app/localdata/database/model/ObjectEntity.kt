package stenograffia.app.localdata.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import stenograffia.app.domain.model.ObjectModel
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintType
import stenograffia.app.localdata.database.typeconverters.ObjectTypeConverter


@Entity
@TypeConverters(ObjectTypeConverter::class)
data class ObjectEntity (
    @PrimaryKey
    var objectId: Int,

    var objectName: String,
    var stateObject: String,
    var timeLabel: Int,
    var cordObject: String,
    var dataStart: Int,
    var historyDataStart: List<Int>,
    var artistOnObject: List<Int>,
    var responsibleForObject: List<Int>,
    var peopleInObject: List<Int>,
    var ordersOnObject: List<Int>,
    ) {}

fun ObjectEntity.toObjectModel() : ObjectModel {
    return ObjectModel(
        objectId = objectId,
        objectName = objectName,
        stateObject = stateObject,
        timeLabel = timeLabel,
        cordObject = cordObject,
        dataStart = dataStart,
        historyDataStart = historyDataStart,
        artistOnObject = artistOnObject,
        responsibleForObject = responsibleForObject,
        peopleInObject = peopleInObject,
        ordersOnObject = ordersOnObject
    )

}

fun ObjectModel.fromObjectEntity() : ObjectEntity {
    return ObjectEntity(
        objectId = objectId,
        objectName = objectName,
        stateObject = stateObject,
        timeLabel = timeLabel,
        cordObject = cordObject,
        dataStart = dataStart,
        historyDataStart = historyDataStart,
        artistOnObject = artistOnObject,
        responsibleForObject = responsibleForObject,
        peopleInObject = peopleInObject,
        ordersOnObject = ordersOnObject
    )
}
