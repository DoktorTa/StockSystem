package stenograffia.app.domain.useCases

import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.AuthTokens
import stenograffia.app.domain.model.UserModel
import stenograffia.app.domain.model.UserRole
import stenograffia.app.domain.repository.IUserNetworkRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
     private val networkUserRepository: IUserNetworkRepository
){
    suspend fun getAuthTokensByCredentials(login: String, password: String):
            ApiResponse<AuthTokens>? {
        val response = networkUserRepository.loginServerByCredentials(login, password)

        return if (response is ApiResponse.Error) {
            null
        } else {
            response
        }
    }

    suspend fun refreshTokens(refreshToken: String): ApiResponse<AuthTokens>? {
        val response = networkUserRepository.refreshTokens(refreshToken)

        return if (response is ApiResponse.Error) {
            null
        } else {
            response
        }
    }

    suspend fun getUser() : UserModel {
        val response = networkUserRepository.getUser()

        return if (response is ApiResponse.Success) {
            response.data
        } else {
            UserModel("Default", UserRole.GUIDE)
        }
    }
}