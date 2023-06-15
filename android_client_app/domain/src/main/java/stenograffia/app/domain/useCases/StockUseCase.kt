package stenograffia.app.domain.useCases

import kotlinx.coroutines.flow.Flow
import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.MaterialModel
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
        val timeLabel = stockDataBaseRepository.getMaxTimeLabel()

        val answer: ApiResponse<List<PaintModel>?> =
            stockNetworkRepository.changeQuantityById(idPaint, quantity, timeLabel)

        if (answer is ApiResponse.Success) {
            if (answer.data == null) {
                return StockChangeQuantityText.ErrorUpdate()
            } else {
                stockDataBaseRepository.updateAllPaint(answer.data)
                return StockChangeQuantityText.CorrectUpdate(quantity.toString())
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

    suspend fun updateMaterialsByTime() {
        val timeLabel = stockDataBaseRepository.getMaxMaterialTimeLabel()
        val updatedMaterials: ApiResponse<List<MaterialModel>> =
            stockNetworkRepository.getMaterialsByTime(timeLabel)

        if (updatedMaterials is ApiResponse.Success) {
            stockDataBaseRepository.addAllMaterials(updatedMaterials.data)
        }
    }

    fun getMaterials(): Flow<List<MaterialModel>> {
        return stockDataBaseRepository.getAllMaterials()
    }

    fun getMaterialById(materialId: Int) : MaterialModel {
        return stockDataBaseRepository.getMaterialById(materialId)
    }

    fun getLocations() : Set<String> {
        val allLocation: MutableSet<String> = stockDataBaseRepository.getLocations()
        allLocation.addAll(setOf("ON STOCK", "NOT ON STOCK"))
        return allLocation
    }

    suspend fun changeLocationMaterial(materialId: Int, newLocation: String) : ChangeLocationMaterialMsg {
        val timeLabel = stockDataBaseRepository.getMaxMaterialTimeLabel()
        val location: ApiResponse<List<MaterialModel>?> = stockNetworkRepository
            .changeLocationMaterial(materialId, newLocation, timeLabel)
        updateMaterialsByTime()

        return if (location is ApiResponse.Success) {
            if (location.data != null) {
                ChangeLocationMaterialMsg.CorrectChange()
            } else {
                ChangeLocationMaterialMsg.ErrorChange()
            }
        } else {
            ChangeLocationMaterialMsg.ErrorConnect()
        }
    }
}

sealed class ChangeLocationMaterialMsg () {
    class CorrectChange() : ChangeLocationMaterialMsg()
    class ErrorChange() : ChangeLocationMaterialMsg()
    class ErrorConnect() : ChangeLocationMaterialMsg()
}