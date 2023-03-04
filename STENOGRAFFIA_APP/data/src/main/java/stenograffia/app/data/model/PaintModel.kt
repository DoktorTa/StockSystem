package stenograffia.app.data.model

data class PaintModel(
    val id: Int,
    val type: TypePaint,
    val nameCreater: String,
    val nameLine: String,
    val namePaint: String,
    val nameColor: String,
    val descriptionColor: String,
    val color: Int,
    val quantityInStorage: Int,
    val placesOfPossibleAvailability: List<String>,
    val similarColors: List<PaintModel>,
    val possibleToBuy: Boolean
)

enum class TypePaint {
    CANS
}

