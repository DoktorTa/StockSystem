package stenograffia.app.data.repository

import stenograffia.app.data.network.AuthApi
import stenograffia.app.data.network.data.LoginRequest
import stenograffia.app.data.network.data.RefreshRequest
import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.AuthTokens
import stenograffia.app.domain.repository.IUserRepository
import java.net.SocketTimeoutException

class UserRepositoryImpl (
    private val authApi: AuthApi
): IUserRepository {

    override suspend fun loginServerByCredentials(login: String, password: String) :
            ApiResponse<AuthTokens>
    {
        try {
            val loginRequest = LoginRequest(login, password)
            val response = authApi.getAccessTokens(loginRequest)

            if (response.code() == 200) {
                val responseToken = response.body()!!
                val authToken = AuthTokens(responseToken.accessToken, responseToken.refreshToken)
                return ApiResponse.Success(data = authToken)
            } else if (response.code() == 403) {
                return ApiResponse.Unauthorized()
            }
            throw RuntimeException()
        } catch (e: RuntimeException) {
            return ApiResponse.Error(e)
        } catch (e: SocketTimeoutException) {
            return ApiResponse.ServerDisconnect()
        }
    }

    override suspend fun refreshTokens(refreshToken: String): ApiResponse<AuthTokens> {
        try {
            val response = authApi.refreshTokens(RefreshRequest(refreshToken))

            if (response.code() == 200) {
                val responseToken = response.body()!!
                val authToken = AuthTokens(responseToken.accessToken, responseToken.refreshToken)
                return ApiResponse.Success(data = authToken)
            } else if (response.code() == 403) {
                return ApiResponse.Unauthorized()
            }
            throw RuntimeException()
        } catch (e: RuntimeException) {
            return ApiResponse.Error(e)
        } catch (e: SocketTimeoutException) {
            return ApiResponse.ServerDisconnect()
        }    }
}