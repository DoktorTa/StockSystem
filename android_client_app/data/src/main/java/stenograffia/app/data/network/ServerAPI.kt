package stenograffia.app.data.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import stenograffia.app.data.network.data.LoginRequest
import stenograffia.app.data.network.data.LoginResponse

interface ServerAPI {

    @POST(Urls.LOGIN)
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET(Urls.TEST)
    suspend fun test(): String

}