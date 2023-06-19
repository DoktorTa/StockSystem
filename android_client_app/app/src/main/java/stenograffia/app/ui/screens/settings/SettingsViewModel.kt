package stenograffia.app.ui.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stenograffia.app.domain.model.UserModel
import stenograffia.app.domain.useCases.UserUseCase
import stenograffia.app.domain.model.UserRole
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    companion object {
        var userName: String = ""
        var userStatus: UserRole? = null
    }

    fun getUserName() : String {
        return userName
    }

    fun getUserStatus() : UserRole? {
        return userStatus
    }

    suspend fun loadUserByAccessToken(){
        val user: UserModel = userUseCase.getUser()
        userName = user.username
        userStatus = user.userRole
    }

    var localeActive = mutableStateOf(Locale("en"))
        private set

    fun setLocale(locale: Locale){
        localeActive.value = locale
    }

    val localeList: Map<String, Locale> = mapOf(
        "en" to Locale("en"),
        "ru" to Locale("ru")
    )

    var isDarkThemeEnabled = mutableStateOf(true)
        private set

    fun setTheme(isDarkTheme: Boolean) {
        isDarkThemeEnabled.value = isDarkTheme
    }
}

