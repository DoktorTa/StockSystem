package stenograffia.app

import androidx.lifecycle.ViewModel

data class Paint(
    val id: Int,
    val type: String,
    val nameCreater: String,
    val nameLine: String,
    val namePaint: String,
    val nameColor: String,
    val descriptionColor: String,
    val color: Int,
    val quantityInStorage: Int,
    val placesOfPossibleAvailability: List<String>,
    val similarColors: List<Paint>
)

class PaintViewModel: ViewModel() {

    fun getPaintObject(): Paint {
        return Paint(
            id = 1234,
            type = "Cans",
            nameCreater = "MONTANA",
            nameLine = "BLACK",
            namePaint = "BLK-8000-400",
            nameColor = "IVORY",
            descriptionColor = "",
            color = 15919300,
            quantityInStorage = 12,
            placesOfPossibleAvailability = listOf("Street №12"),
            similarColors = listOf()
            )
    }



}