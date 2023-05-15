package stenograffia.app.domain.useCases

import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.AuthTokens
import stenograffia.app.domain.model.UserModel
import stenograffia.app.domain.repository.IUserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
     private val networkUserRepository: IUserRepository
){

    suspend fun getAuthTokensByCredentials(login: String, password: String): AuthTokens? {
        val response: ApiResponse<Pair<AuthTokens, UserModel>> = networkUserRepository.loginServerByCredentials(login, password)
        if (response is ApiResponse.Error) {
            if (response.exception.toString() == "Invalid credentials") {
                return AuthTokens("Invalid credentials", "ssaa")
            }
        } else if (response is ApiResponse.Success) {
            return response.data.first
        }
        return null
    }
}