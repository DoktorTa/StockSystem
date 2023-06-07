package stenograffia.app.data.repository

import stenograffia.app.data.network.ServerAPI
import stenograffia.app.data.network.data.UpdateQuantityRequest
import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.repository.IStockNetworkRepository
import java.net.SocketTimeoutException
import javax.inject.Inject

class StockNetworkRepositoryImpl @Inject constructor(
    val serverAPI: ServerAPI
) : IStockNetworkRepository {

    override suspend fun updateQuantityById(idPaint: Int, quantity: Int) : ApiResponse<PaintModel?>{
        try {
            val updateQuantityRequest = UpdateQuantityRequest(idPaint, quantity)
            val response = serverAPI.updatePaintQuantity(updateQuantityRequest)

            if (response.code() == 200) {
                return ApiResponse.Success(response.body()!!.paint.toPaintModel())
            } else if (response.code() == 400) {
                return ApiResponse.Success(null)
            }
            throw RuntimeException()
        } catch (e: RuntimeException) {
            return ApiResponse.Error(e)
        } catch (e: SocketTimeoutException) {
            return ApiResponse.ServerDisconnect()
        }
    }

}