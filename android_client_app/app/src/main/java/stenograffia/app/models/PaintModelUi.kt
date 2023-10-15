package stenograffia.app.models

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintType
import stenograffia.app.utils.IDiffQuantity

@Stable
data class PaintModelUi(
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
    val possibleToBuy: Boolean,

    val uv_resistance: String = "?",
    val contrastTextOnColor: Color = Color(0xFF000000 + color).getColorText()
) : IDiffQuantity {
    override fun changeQuantity(id: Int, difference: Int) {
        quantityInStorage += difference
    }

}

fun PaintModel.toPaintModelUi() : PaintModelUi {
    return PaintModelUi(
        id = id,
        type = type,
        timeLabel = timeLabel,
        nameCreator = nameCreator,
        nameLine = nameLine,
        codePaint = codePaint,
        nameColor = nameColor,
        descriptionColor = descriptionColor,
        color = color,
        quantityInStorage = quantityInStorage,
        similarColors = similarColors,
        possibleToBuy = possibleToBuy,
    )
}

private fun Color.getColorText() : Color {
    val labColorText = this.toArgb()
    val contrastOnBlack = ColorUtils.calculateContrast(labColorText, Color.Black.toArgb())
    val contrastOnWhite = ColorUtils.calculateContrast(labColorText, Color.White.toArgb())

    return if (contrastOnBlack < contrastOnWhite) {
        Color.White
    } else {
        Color.Black
    }
}