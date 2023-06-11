package stenograffia.app.data.repository

import android.util.Log
import stenograffia.app.data.network.ServerAPI
import stenograffia.app.data.network.data.GetPaintRequest
import stenograffia.app.data.network.data.UpdateQuantityRequest
import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.repository.IStockNetworkRepository
import java.net.SocketTimeoutException
import javax.inject.Inject

class StockNetworkRepositoryImpl @Inject constructor(
    val serverApi: ServerAPI
) : IStockNetworkRepository {

    override suspend fun updateQuantityById(idPaint: Int, quantity: Int) : ApiResponse<PaintModel?>{
        try {
            val updateQuantityRequest = UpdateQuantityRequest(idPaint, quantity)
            val response = serverApi.updatePaintQuantity(updateQuantityRequest)

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

    override suspend fun getAllPaintsByTime(time: Int) : ApiResponse<List<PaintModel>> {
        try {
            val request = GetPaintRequest(time)
            val response = serverApi.getAllPaintByTime(request)

            if (response.code() == 200) {
                return ApiResponse.Success(response.body()!!.paints.map {it.toPaintModel()})
            } else if (response.code() == 400) {
                return ApiResponse.Success(listOf())
            }
            throw RuntimeException()
        } catch (e: RuntimeException) {
            return ApiResponse.Error(e)
        } catch (e: SocketTimeoutException) {
            return ApiResponse.ServerDisconnect()
        }
    }

}