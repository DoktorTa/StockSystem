package stenograffia.app.domain.useCases

import android.util.Log
import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.repository.IStockDataBaseRepository
import stenograffia.app.domain.repository.IStockNetworkRepository
import javax.inject.Inject

class StockUseCase @Inject constructor(
    private val stockNetworkRepository: IStockNetworkRepository,
    private val stockDataBaseRepository: IStockDataBaseRepository
) {

    suspend fun updateQuantityById(idPaint: Int, quantity: Int): PaintModel? {
        // {"paint":
        // {
        // "name_creator":"MONTANA CANS",
        // "data_time":1685876080,
        // "paint_type":"CANS",
        // "name_color":"Smash137Вґs Potato",
        // "description_color":" ",
        // "quantity_in_storage":35,
        // "name_line":"BLACK - 400ml",
        // "paint_id":110000,
        // "code_paint":"BLK-1005-400",
        // "color":16049555,
        // "possible_to_buy":false,
        // "similar_colors":[[120000,97],[210000,97]]}}

        val answer: ApiResponse<PaintModel?> =
            stockNetworkRepository.updateQuantityById(idPaint, quantity)

        if (answer is ApiResponse.Success) {
            if (answer.data == null) {
                return null
            } else {
                stockDataBaseRepository.updatePaint(answer.data)
            }
        }
        return null
    }
}