package stenograffia.app.data.repository

import android.util.Log
import stenograffia.app.data.network.ServerAPI
import stenograffia.app.data.network.data.LoginRequest
import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.AuthTokens
import stenograffia.app.domain.model.UserModel
import stenograffia.app.domain.repository.IUserRepository

class UserRepositoryImpl (
    val serverApi: ServerAPI
): IUserRepository {

    override suspend fun loginServerByCredentials(login: String, password: String) :
            ApiResponse<Pair<AuthTokens, UserModel>>
    {
        try {
            Log.d("Credits", "${login}")
//            test()
            val loginRequest = LoginRequest(login, password)
            val response = serverApi.login(loginRequest)

            if (response.code() == 200) {
                val responseToken = response.body()!!
                val authToken = AuthTokens(responseToken.accessToken, responseToken.refreshToken)
                val userModel = UserModel(responseToken.userName, responseToken.userGroup)

                return ApiResponse.Success(data = Pair(authToken, userModel))
            } else if (response.code() == 403) {
                return ApiResponse.Error(Exception("Invalid credentials"))
            }
            throw RuntimeException()
        } catch (e: RuntimeException) {
            return ApiResponse.Error(e)
        }
    }

    private suspend fun test(){
        try {
            val rest =  serverApi.test()
            Log.d("Test", rest)
        } catch (e: RuntimeException){
            Log.d("Test, Ex", e.toString())
        }

    }
}
