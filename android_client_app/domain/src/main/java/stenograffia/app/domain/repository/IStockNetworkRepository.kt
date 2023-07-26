package stenograffia.app.domain.repository

import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.MaterialModel
import stenograffia.app.domain.model.PaintModel

interface IStockNetworkRepository {

    suspend fun changeQuantityById(idPaint: Int, quantity: Int, timeLabel: Int) : ApiResponse<List<PaintModel>?>

    suspend fun getAllPaintsByTime(time: Int) : ApiResponse<List<PaintModel>>

    suspend fun getMaterialsByTime(timeLabel: Int) : ApiResponse<List<MaterialModel>>

    suspend fun changeMaterial(materialId: Int, location: String, diffQuantity: Int, timeLabel: Int) : ApiResponse<List<MaterialModel>?>

}