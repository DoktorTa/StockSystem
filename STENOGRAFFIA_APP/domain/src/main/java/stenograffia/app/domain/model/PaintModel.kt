package stenograffia.app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PaintModel(
    val id: Int,
    val type: TypePaint,
    val nameCreator: String,
    val nameLine: String,
    val codePaint: String,
    val nameColor: String,
    val descriptionColor: String,
    val color: Int,
    var quantityInStorage: Int,
    var placesOfPossibleAvailability: List<Int>,
    var similarColors: List<List<Int>>,
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

