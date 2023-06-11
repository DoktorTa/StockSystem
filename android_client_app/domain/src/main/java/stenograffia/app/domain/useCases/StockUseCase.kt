package stenograffia.app.domain.useCases

import android.util.Log
import kotlinx.coroutines.flow.Flow
import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.repository.IStockDataBaseRepository
import stenograffia.app.domain.repository.IStockNetworkRepository
import stenograffia.app.domain.utils.StockChangeQuantityText
import javax.inject.Inject

class StockUseCase @Inject constructor(
    private val stockNetworkRepository: IStockNetworkRepository,
    private val stockDataBaseRepository: IStockDataBaseRepository
) {

    suspend fun updateQuantityById(idPaint: Int, quantity: Int): StockChangeQuantityText {

        val answer: ApiResponse<PaintModel?> =
            stockNetworkRepository.updateQuantityById(idPaint, quantity)

        if (answer is ApiResponse.Success) {
            if (answer.data == null) {
                return StockChangeQuantityText.ErrorUpdate()
            } else {
                stockDataBaseRepository.updateAllPaint(listOf(answer.data))
                return StockChangeQuantityText
                    .CorrectUpdate(answer.data.quantityInStorage.toString())
            }
        }
        return StockChangeQuantityText.ErrorConnect()
    }

    suspend fun updatePaintsByTime() {
        val timeLabel = stockDataBaseRepository.getMaxTimeLabel()
        val updatedPaints: ApiResponse<List<PaintModel>> =
            stockNetworkRepository.getAllPaintsByTime(timeLabel)

        if (updatedPaints is ApiResponse.Success){
            stockDataBaseRepository.updateAllPaint(updatedPaints.data)
        }
    }

    fun getPaintModelById(paintId: Int): PaintModel? {
        return stockDataBaseRepository.getPaintById(paintId)
    }

    fun getLinePaint(paintNameModel: PaintNamesTupleModel): Flow<List<PaintModel>> {
        return stockDataBaseRepository
            .getPaintsListByCreatorAndLine(paintNameModel.nameCreator, paintNameModel.nameLine)
    }

    fun getAllPaintNames(): Flow<List<PaintNamesTupleModel>> {
        return stockDataBaseRepository.getAllPaintNames()
    }
}