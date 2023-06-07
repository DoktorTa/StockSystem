package stenograffia.app.data.network.data

import com.google.gson.annotations.SerializedName
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.TypePaint

data class SinglePaintResponse(
    @SerializedName("paint")
    var paint: PaintModelNetwork
) {}

data class PaintModelNetwork(
    var name_creator: String,
    var data_time: Int,
    var paint_type: String,
    var name_color: String,
    var description_color: String,
    var quantity_in_storage: Int,
    var name_line: String,
    var paint_id: Int,
    var code_paint: String,
    var color: Int,
    var possible_to_buy: Boolean,
    var similar_colors: List<List<Int>>
) {
    fun toPaintModel(): PaintModel{
        return PaintModel(
            id = paint_id,
            type = TypePaint.createByString(paint_type),
            nameCreator = name_creator,
            nameLine = name_line,
            codePaint = code_paint,
            nameColor = name_color,
            descriptionColor = description_color,
            color = color,
            quantityInStorage = quantity_in_storage,
            placesOfPossibleAvailability = listOf(),
            similarColors = similar_colors,
            possibleToBuy = possible_to_buy
        )
    }
}