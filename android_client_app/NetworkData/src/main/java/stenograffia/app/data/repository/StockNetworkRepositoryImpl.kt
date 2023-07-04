package stenograffia.app.data.repository

import stenograffia.app.data.network.StockApi
import stenograffia.app.data.network.UserApi
import stenograffia.app.data.network.data.ChangeLocationRequest
import stenograffia.app.data.network.data.ChangeQuantityPaint
import stenograffia.app.data.network.data.TimeRequest
import stenograffia.app.data.network.model.toMaterialModel
import stenograffia.app.data.network.model.toPaintModel
import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.MaterialModel
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.repository.IStockNetworkRepository
import java.net.SocketTimeoutException
import javax.inject.Inject

class StockNetworkRepositoryImpl @Inject constructor(
    private val stockApi: StockApi
) : IStockNetworkRepository {

    override suspend fun changeQuantityById(idPaint: Int, quantity: Int, timeLabel: Int) : ApiResponse<List<PaintModel>?> {
        try {
            val updateQuantityRequest = ChangeQuantityPaint(idPaint, quantity, timeLabel)
            val response = stockApi.changeQuantityPaint(updateQuantityRequest)

            if (response.code() == 200) {
                return ApiResponse.Success(response.body()!!.paints.map {it.toPaintModel()})
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
            val request = TimeRequest(time)
            val response = stockApi.getAllPaintByTime(request)

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

    override suspend fun getMaterialsByTime(timeLabel: Int): ApiResponse<List<MaterialModel>> {
        try {
            val request = TimeRequest(timeLabel)
            val response = stockApi.getMaterial(request)

            if (response.code() == 200) {
                return ApiResponse.Success(response.body()!!.material.map {it.toMaterialModel()})
            } else if (response.code() == 400) {
                return ApiResponse.Success(listOf())
            }
            throw RuntimeException()
        } catch (e: RuntimeException) {
            return ApiResponse.Error(e)
        } catch (e: SocketTimeoutException) {
            return ApiResponse.ServerDisconnect()
        }    }

    override suspend fun changeLocationMaterial(
        materialId: Int,
        location: String,
        timeLabel: Int
    ): ApiResponse<List<MaterialModel>?> {
        try {
            val updateQuantityRequest = ChangeLocationRequest(
                materialId = materialId,
                location = location,
                timeLabel = timeLabel
            )
            val response = stockApi.changeLocationMaterial(updateQuantityRequest)

            if (response.code() == 200) {
                return ApiResponse.Success(response.body()!!.material.map {it.toMaterialModel()})
            } else if (response.code() == 400) {
                return ApiResponse.Success(null)
            }
            throw RuntimeException()
        } catch (e: RuntimeException) {
            return ApiResponse.Error(e)
        } catch (e: SocketTimeoutException) {
            return ApiResponse.ServerDisconnect()
        }    }

}