package stenograffia.app.stock.paint

import android.util.Log
import androidx.annotation.ColorInt
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.navigation.compose.rememberNavController
import stenograffia.app.Paint
import stenograffia.app.data.model.PaintModel
import stenograffia.app.ui.theme.black

data class PaintItem(
    val id: Int,
    val nameColor: String,
    val namePaint: String,
    val color: Color,
    val statusPaint: String,

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

        fun fromPaintModel(paintModel: PaintModel): PaintItem{
            val statusPaint = getStatusPaint(paintModel)

            val color = Color(0xFF000000 + paintModel.color)
            return PaintItem(
                id = paintModel.id,
                nameColor = paintModel.nameColor,
                namePaint = paintModel.namePaint,
                color = color,
                statusPaint = statusPaint,
                colorText = getColorText(color.toArgb()),
                colorTextStatus = colorTextStatuses[statusPaint]!!
            )
        }

        private fun getColorText(labColorText: Int): Color {
            val contrastOnBlack = ColorUtils.calculateContrast(labColorText, BLACK_RGB)
            val contrastOnWhite = ColorUtils.calculateContrast(labColorText, WHITE_RGB)
            Log.d("CONTARAST", "Black: $contrastOnBlack, White: $contrastOnWhite")

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
    Log.d("COLOR COLOR SQUARE", "${paintItem.color}")

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
                Text(text = paintItem.namePaint, color = paintItem.colorText, modifier = modifier1, maxLines = 1)
            }
            TextStatus(text = paintItem.statusPaint, color = paintItem.colorTextStatus, modifier = modifier1)
        }
}

@Composable
fun TextStatus(text: String, color: Color, modifier: Modifier = Modifier){
    Text(text = text, color = color, style = MaterialTheme.typography.body1, modifier = modifier)
}