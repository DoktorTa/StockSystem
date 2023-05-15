package stenograffia.app.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stenograffia.app.domain.useCases.UserUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    var infoText: String = ""

    fun singIn(login: String, password: String){
        Log.d("LoginViewModel", "1")
        viewModelScope.launch {
            Log.d("LoginViewModel", "2")
            val authTokens = userUseCase.getAuthTokensByCredentials(login, password)
            Log.d("LoginViewModel", "3, ${authTokens}")
            if (authTokens != null){
                infoText = authTokens.accessToken
            } else {
                infoText = "error"
            }
        }
    }
}