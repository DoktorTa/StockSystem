package stenograffia.app.data.network.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import stenograffia.app.data.network.Urls
import stenograffia.app.data.network.data.ElementObjectsResponse
import stenograffia.app.data.network.data.TimeRequest

interface ObjectApi {

    @POST(Urls.GET_OBJECT_BY_TIME)
    suspend fun getAllObjectsByTime(@Body request: TimeRequest): Response<ElementObjectsResponse>
}