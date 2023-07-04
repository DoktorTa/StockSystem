package stenograffia.app.data.network

import retrofit2.Response
import retrofit2.http.POST
import stenograffia.app.data.network.data.GetUserResponse

interface UserApi {

    @POST(Urls.GET_USER_BY_TOKEN)
    suspend fun getUser(): Response<GetUserResponse>

}