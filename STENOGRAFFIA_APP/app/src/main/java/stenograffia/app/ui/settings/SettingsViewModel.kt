package stenograffia.app.ui.settings

import android.os.LocaleList
import android.provider.ContactsContract.Contacts.Data
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.intellij.lang.annotations.Language
import java.time.LocalTime
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SettingsViewModel @Inject constructor(): ViewModel() {
    val userName: String = "User name"
    val userStatus: String = "ARTIST"

    var isDarkThemeEnabled = mutableStateOf(true)
        private set

    fun setTheme(isDarkTheme: Boolean) {
        isDarkThemeEnabled.value = isDarkTheme
    }
}

