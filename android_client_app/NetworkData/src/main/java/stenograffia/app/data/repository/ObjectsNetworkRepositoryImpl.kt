package stenograffia.app.data.repository

import stenograffia.app.data.network.api.ObjectApi
import stenograffia.app.data.network.data.TimeRequest
import stenograffia.app.data.network.model.toObjectModel
import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.ObjectModel
import stenograffia.app.domain.repository.IObjectNetworkRepository
import java.net.SocketTimeoutException
import javax.inject.Inject

class ObjectsNetworkRepositoryImpl @Inject constructor(
    private val objectsApi: ObjectApi
) : IObjectNetworkRepository {

    override suspend fun getAllObjectsByTime(time: Int): ApiResponse<List<ObjectModel>> {
        try {
            val request = TimeRequest(time)
            val response = objectsApi.getAllObjectsByTime(request)

            if (response.code() == 200) {
                return ApiResponse.Success(response.body()!!.objects.map {it.toObjectModel()})
            } else if (response.code() == 400) {
                return ApiResponse.Success(listOf())
            }
            throw RuntimeException()
        } catch (e: RuntimeException) {
            return ApiResponse.Error(e)
        } catch (e: SocketTimeoutException) {
            return ApiResponse.ServerDisconnect()
        }    }

}