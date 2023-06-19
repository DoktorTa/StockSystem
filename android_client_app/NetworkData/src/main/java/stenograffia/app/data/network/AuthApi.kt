package stenograffia.app.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import stenograffia.app.data.network.data.GetUserResponse
import stenograffia.app.data.network.data.LoginRequest
import stenograffia.app.data.network.data.LoginResponse
import stenograffia.app.data.network.data.RefreshRequest

interface AuthApi {

    @POST(Urls.GET_ACCESS_TOKEN)
    suspend fun getAccessTokens(@Body request: LoginRequest): Response<LoginResponse>

    @POST(Urls.REFRESH_TOKEN)
    suspend fun refreshTokens(@Body request: RefreshRequest): Response<LoginResponse>

}