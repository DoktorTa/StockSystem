package stenograffia.app.ui.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import stenograffia.app.domain.model.AuthTokens
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(): ViewModel() {
    var loginCorrect: MutableLiveData<Boolean> = MutableLiveData(false)
        private set

    fun setLoginCorrect(loginCorrectValue: Boolean){
        loginCorrect.value = loginCorrectValue
    }

    var authTokens: AuthTokens? = null

    val userName: String = "User name"
    val userStatus: String = "ARTIST"

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

