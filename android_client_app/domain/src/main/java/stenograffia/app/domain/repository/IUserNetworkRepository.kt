package stenograffia.app.domain.repository

import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.AuthTokens
import stenograffia.app.domain.model.UserModel

interface IUserNetworkRepository {
    suspend fun loginServerByCredentials(login: String, password: String): ApiResponse<AuthTokens>

    suspend fun refreshTokens(refreshToken: String): ApiResponse<AuthTokens>

    suspend fun getUser() : ApiResponse<UserModel>
}