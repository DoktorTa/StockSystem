package stenograffia.app.stock.paint

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import stenograffia.app.domain.model.PaintModel

data class PaintItem(
    val id: Int,
    val nameColor: String,
    val codePaint: String,
    val color: Color,
    val statusPaint: String,

    val additionalInformation: String,

    val colorText: Color,
    val colorTextStatus: Color
) {
    companion object {
        private val WHITE_RGB: Int = Color.Black.toArgb()
        private val BLACK_RGB: Int = Color.White.toArgb()

        private const val NOT_AVAILABLE: String = "NOT AVAILABLE"
        private const val MAYBE: String = "MAYBE"
        private const val ON_STOCK: String = "ON STOCK"

        private val colorTextStatuses: Map<String, Color> = mapOf(
            NOT_AVAILABLE to Color.Red,
            MAYBE to Color.Blue,
            ON_STOCK to Color.Green
        )

        fun fromPaintModel(paintModel: PaintModel, additionalInformation: String = ""): PaintItem{
            val statusPaint = getStatusPaint(paintModel)

            val color = Color(0xFF000000 + paintModel.color)
            return PaintItem(
                id = paintModel.id,
                nameColor = paintModel.nameColor,
                codePaint = paintModel.codePaint,
                color = color,
                statusPaint = statusPaint,
                additionalInformation = additionalInformation,
                colorText = getColorText(color.toArgb()),
                colorTextStatus = colorTextStatuses[statusPaint]!!
            )
        }

        private fun getColorText(labColorText: Int): Color {
            val contrastOnBlack = ColorUtils.calculateContrast(labColorText, BLACK_RGB)
            val contrastOnWhite = ColorUtils.calculateContrast(labColorText, WHITE_RGB)

            return if (contrastOnBlack > contrastOnWhite){
                Color.White
            } else {
                Color.Black
            }
        }

        private fun getStatusPaint(paintModel: PaintModel): String {
            return if (paintModel.possibleToBuy && paintModel.quantityInStorage == 0){
                MAYBE
            } else if (!paintModel.possibleToBuy && paintModel.quantityInStorage == 0){
                NOT_AVAILABLE
            } else {
                ON_STOCK
            }
        }
    }
}

@Composable
fun ListPaintItem(paintItem: PaintItem, modifier: Modifier=Modifier, width: Int = 90) {
    val modifier1 = Modifier.padding(start = 2.dp)

        Column (
            modifier = modifier
                .wrapContentHeight()
                .background(color = MaterialTheme.colors.primary)) {
            Column (
                modifier = Modifier
                    .height(60.dp)
                    .background(color = paintItem.color)
                    .fillMaxWidth()) {
                Text(text = paintItem.nameColor, color = paintItem.colorText, modifier = modifier1, maxLines = 1)
                Text(text = paintItem.codePaint, color = paintItem.colorText, modifier = modifier1, maxLines = 1)
            }
            TextStatus(text = paintItem.statusPaint, color = paintItem.colorTextStatus, modifier = modifier1)
        }
}

@Composable
fun TextStatus(text: String, color: Color, modifier: Modifier = Modifier){
    Text(text = text, color = color, style = MaterialTheme.typography.body1, modifier = modifier)
}