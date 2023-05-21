package stenograffia.app.ui.login


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stenograffia.app.domain.ApiResponse
import stenograffia.app.domain.model.AuthTokens
import stenograffia.app.domain.useCases.UserUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    val infoText: MutableLiveData<String> = MutableLiveData("")
    val loginCorrect: MutableLiveData<Boolean> = MutableLiveData(false)
    val serverConnect: MutableLiveData<Boolean> = MutableLiveData(true)

    fun singIn(login: String, password: String){
        viewModelScope.launch {
            val response: ApiResponse<AuthTokens>? = userUseCase.getAuthTokensByCredentials(login, password)

            if (response is ApiResponse.Success){
                infoText.value = response.data.accessToken
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
}