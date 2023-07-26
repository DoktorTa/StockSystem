package stenograffia.app.domain.model

data class PaintModel(
    val id: Int,
    val type: PaintType,
    val timeLabel: Int,
    val nameCreator: String,
    val nameLine: String,
    val codePaint: String,
    val nameColor: String,
    val descriptionColor: String,
    val color: Int,
    var quantityInStorage: Int,
    var similarColors: List<List<Int>>,
    val possibleToBuy: Boolean
)


