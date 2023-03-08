package stenograffia.app.domain.model


data class PaintModel(
    val id: Int,
    val type: TypePaint,
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

enum class TypePaint {
    CANS,
    BUCKET;

    companion object {
        fun createByString(name: String): TypePaint {
            return when (name) {
                "CANS" -> CANS
                else -> BUCKET
            }
        }
    }
}

