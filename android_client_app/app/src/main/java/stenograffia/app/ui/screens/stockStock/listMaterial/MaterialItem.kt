package stenograffia.app.ui.screens.stockStock.listMaterial

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import stenograffia.app.domain.model.MaterialModel

@Immutable
data class MaterialItem(
    val id: Int = 0,
    val type: String = "",
    val location: String = "",

    val color: Color = Color.Gray,
    val unique: Boolean = true,
    val colorTextLocation: Color = Color.White
) {
    companion object {
        fun fromPaintModel(materialModel: MaterialModel): MaterialItem {
            val colorTextLocation = getColorTextLocation(materialModel.location)
            return MaterialItem(
                id = materialModel.id,
                type = materialModel.type,
                location = materialModel.location,

                unique = materialModel.unique,
                colorTextLocation = colorTextLocation
            )
        }

        private fun getColorTextLocation(location: String) : Color {
            // TODO: Когда буду править локализацию переделаю из хардкор текста
            return if (location == "ON STOCK") {
                Color.Green
            } else {
                Color.Blue
            }
        }

    }
}
