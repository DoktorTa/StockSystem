package stenograffia.app.domain.repository

import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.PaintModel

interface IStockNetworkRepository {

    suspend fun updateQuantityById(idPaint: Int, quantity: Int): ApiResponse<PaintModel?>
}