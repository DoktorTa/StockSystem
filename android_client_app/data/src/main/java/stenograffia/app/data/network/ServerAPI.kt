package stenograffia.app.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import stenograffia.app.data.network.data.*

interface ServerAPI {

    @POST(Urls.GET_ACCESS_TOKEN)
    suspend fun getAccessTokens(@Body request: LoginRequest): Response<LoginResponse>

    @POST(Urls.REFRESH_TOKEN)
    suspend fun refreshTokens(@Body request: RefreshRequest): Response<LoginResponse>

    @POST(Urls.UPDATE_PAINT_QUANTITY)
    suspend fun updatePaintQuantity(@Body request: UpdateQuantityRequest): Response<SinglePaintResponse>

    @POST(Urls.GET_PAINTS_BY_TIME)
    suspend fun getAllPaintByTime(@Body request: GetPaintRequest): Response<ManyPaintResponse>

}