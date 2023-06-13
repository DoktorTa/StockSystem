package stenograffia.app.domain.repository

import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.PaintModel

interface IStockNetworkRepository {

    suspend fun changeQuantityById(idPaint: Int, quantity: Int): ApiResponse<List<PaintModel>?>

    suspend fun getAllPaintsByTime(time: Int) : ApiResponse<List<PaintModel>>
}