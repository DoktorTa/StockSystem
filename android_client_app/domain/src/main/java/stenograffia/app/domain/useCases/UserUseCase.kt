package stenograffia.app.domain.useCases

import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.AuthTokens
import stenograffia.app.domain.repository.IUserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
     private val networkUserRepository: IUserRepository
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
}