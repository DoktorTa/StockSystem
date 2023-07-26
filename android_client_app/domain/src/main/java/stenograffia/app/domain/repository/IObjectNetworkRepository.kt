package stenograffia.app.domain.repository

import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.ObjectModel

interface IObjectNetworkRepository {
    suspend fun getAllObjectsByTime(time: Int) : ApiResponse<List<ObjectModel>>
}