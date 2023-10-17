package stenograffia.app.data.network.model

import stenograffia.app.domain.model.ObjectModel

data class ObjectModelNetwork(
    var object_id: Int,
    var object_name: String,
    var state_object: String,
    var time_label: Int,
    var cord_object: String,
    var data_start: Int,
    var history_data_start: List<Int>,
    var artist_on_object: List<Int>,
    var responsible_for_object: List<Int>,
    var people_in_object: List<Int>,
    var orders_on_object: List<Int>,
) {}

fun ObjectModelNetwork.toObjectModel(): ObjectModel {
    return ObjectModel(
        objectId = object_id,
        objectName = object_name,
        stateObject = state_object,
        timeLabel = time_label,
        cordObject = cord_object,
        dataStart = data_start,
        historyDataStart = history_data_start,
        artistOnObject = artist_on_object,
        responsibleForObject = responsible_for_object,
        peopleInObject = people_in_object,
        ordersOnObject = orders_on_object
    )
}
