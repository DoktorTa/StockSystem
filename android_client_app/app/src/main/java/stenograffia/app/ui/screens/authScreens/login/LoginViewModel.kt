package stenograffia.app.ui.screens.authScreens.login


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.AuthTokens
import stenograffia.app.domain.useCases.UserUseCase
import stenograffia.app.data.DataStoreToken
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    val dataStoreToken: DataStoreToken
): ViewModel() {

    val infoText: MutableLiveData<String> = MutableLiveData("")
    val loginCorrect: MutableLiveData<Boolean> = MutableLiveData(false)
    val serverConnect: MutableLiveData<Boolean> = MutableLiveData(true)
    var authTokens: AuthTokens? = null

    fun singIn(login: String, password: String){
        viewModelScope.launch {
            val response: ApiResponse<AuthTokens>? = userUseCase.getAuthTokensByCredentials(login, password)
            processingAnswerToken(response)
        }
    }

    fun refreshTokens(refreshToken: String){
        viewModelScope.launch {
            val response: ApiResponse<AuthTokens>? = userUseCase.refreshTokens(refreshToken)
            processingAnswerToken(response)
        }
    }

    private fun processingAnswerToken(response: ApiResponse<AuthTokens>?){
        if (response is ApiResponse.Success){
            infoText.value = response.data.accessToken
            authTokens = response.data
            loginCorrect.value = true
            serverConnect.value = true
        } else if (response is ApiResponse.Unauthorized) {
            infoText.value = "Invalid Credits"
            loginCorrect.value = false
        } else if (response is ApiResponse.ServerDisconnect) {
            infoText.value = "Server disconnect"
            serverConnect.value = false
        }
    }
}