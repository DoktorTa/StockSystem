package stenograffia.app.domain.model

data class ObjectModel(
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
)


