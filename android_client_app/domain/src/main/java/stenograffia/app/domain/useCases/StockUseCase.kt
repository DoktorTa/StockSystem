package stenograffia.app.domain.useCases

import android.util.Log
import kotlinx.coroutines.flow.Flow
import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.MaterialModel
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.repository.IObjectDataBaseRepository
import stenograffia.app.domain.repository.IStockDataBaseRepository
import stenograffia.app.domain.repository.IStockNetworkRepository
import stenograffia.app.domain.utils.ChangeMsg
import javax.inject.Inject

class StockUseCase @Inject constructor(
    private val stockNetworkRepository: IStockNetworkRepository,
    private val stockDataBaseRepository: IStockDataBaseRepository,
    private val objectDataBaseRepository: IObjectDataBaseRepository
) {

    suspend fun updateQuantityById(idPaint: Int, quantity: Int): ChangeMsg {
        val timeLabel = stockDataBaseRepository.getMaxTimeLabel()
        val answer: ApiResponse<List<PaintModel>?> =
            stockNetworkRepository.changeQuantityById(idPaint, quantity, timeLabel)
        updatePaintsByTime()

        return if (answer is ApiResponse.Success) {
            if (answer.data != null) {
                ChangeMsg.CorrectChange()
            } else {
                ChangeMsg.ErrorChange()
            }
        } else {
            ChangeMsg.ErrorConnect()
        }
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

    fun getLocations(): Set<String> {
        return objectDataBaseRepository.getLocations()
    }

    suspend fun changeQuantityMaterial(materialId: Int, diffQuantity: Int) : ChangeMsg {
        val timeLabel = stockDataBaseRepository.getMaxMaterialTimeLabel()
        val answer: ApiResponse<List<MaterialModel>?> =
            stockNetworkRepository.changeMaterial(materialId, "", diffQuantity, timeLabel)
        updateMaterialsByTime()

        return if (answer is ApiResponse.Success) {
            if (answer.data != null) {
                ChangeMsg.CorrectChange()
            } else {
                ChangeMsg.ErrorChange()
            }
        } else {
            ChangeMsg.ErrorConnect()
        }
    }

    suspend fun changeLocationMaterial(materialId: Int, newLocation: String) : ChangeMsg {
        val timeLabel = stockDataBaseRepository.getMaxMaterialTimeLabel()
        val location: ApiResponse<List<MaterialModel>?> = stockNetworkRepository
            .changeMaterial(materialId, newLocation, 0, timeLabel)
        updateMaterialsByTime()

        return if (location is ApiResponse.Success) {
            if (location.data != null) {
                ChangeMsg.CorrectChange()
            } else {
                ChangeMsg.ErrorChange()
            }
        } else {
            ChangeMsg.ErrorConnect()
        }
    }
}
