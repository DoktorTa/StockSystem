package stenograffia.app.ui.screens.stock.listPaint

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import stenograffia.app.R
import androidx.core.graphics.ColorUtils
import stenograffia.app.domain.model.PaintModel

@Immutable
data class PaintItem(
    val id: Int = 0,
    val nameColor: String = "",
    val codePaint: String = "",
    val color: Color = Color.Black,
    val statusPaintTextId: Int = R.string.paint_list_item_not_available,

    val additionalInformation: String = "",

    val colorText: Color = Color.White,
    val colorTextStatus: Color = Color.White
) {
    companion object {
        private val WHITE_RGB: Int = Color.Black.toArgb()
        private val BLACK_RGB: Int = Color.White.toArgb()

        private const val NOT_AVAILABLE: Int =
            R.string.paint_list_item_not_available //"NOT AVAILABLE"
        private const val MAYBE: Int = R.string.paint_list_item_mabye
        private const val ON_STOCK: Int = R.string.paint_list_item_on_stock

        private val colorTextStatuses: Map<Int, Color> = mapOf(
            NOT_AVAILABLE to Color.Red,
            MAYBE to Color.Blue,
            ON_STOCK to Color.Green
        )

        fun fromPaintModel(paintModel: PaintModel, additionalInformation: String = ""): PaintItem {
            val statusPaint = getStatusPaintTextId(paintModel)

            val color = Color(0xFF000000 + paintModel.color)
            return PaintItem(
                id = paintModel.id,
                nameColor = paintModel.nameColor,
                codePaint = paintModel.codePaint,
                color = color,
                statusPaintTextId = statusPaint,
                additionalInformation = additionalInformation,
                colorText = getColorText(color.toArgb()),
                colorTextStatus = colorTextStatuses[statusPaint]!!
            )
        }

        private fun getColorText(labColorText: Int): Color {
            val contrastOnBlack = ColorUtils.calculateContrast(labColorText, BLACK_RGB)
            val contrastOnWhite = ColorUtils.calculateContrast(labColorText, WHITE_RGB)

            return if (contrastOnBlack > contrastOnWhite) {
                Color.White
            } else {
                Color.Black
            }
        }

        private fun getStatusPaintTextId(paintModel: PaintModel): Int {
            return if (paintModel.possibleToBuy && paintModel.quantityInStorage == 0) {
                MAYBE
            } else if (!paintModel.possibleToBuy && paintModel.quantityInStorage == 0) {
                NOT_AVAILABLE
            } else {
                ON_STOCK
            }
        }
    }
}
