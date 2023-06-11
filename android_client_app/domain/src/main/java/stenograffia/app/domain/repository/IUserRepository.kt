package stenograffia.app.domain.repository

import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.AuthTokens

interface IUserRepository {
    suspend fun loginServerByCredentials(login: String, password: String): ApiResponse<AuthTokens>

    suspend fun refreshTokens(refreshToken: String): ApiResponse<AuthTokens>
}