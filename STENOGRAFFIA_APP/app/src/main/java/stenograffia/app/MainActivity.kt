package stenograffia.app

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import stenograffia.app.ui.settings.DataStoreSettings
import stenograffia.app.ui.settings.SettingsViewModel
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme
import java.time.LocalTime
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        
        val locale = Locale.ENGLISH
        val configuration = baseContext.resources.configuration
        configuration.setLocale(locale)
        baseContext.resources.updateConfiguration(configuration, null)


        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            LoadSettings(settingsViewModel)

            STENOGRAFFIAAPPTheme(
                darkTheme = settingsViewModel.isDarkThemeEnabled.value
            ) {
                StenograffiaApp(settingsViewModel)
            }
        }
    }

    @Composable
    private fun LoadSettings(settingsViewModel: SettingsViewModel){
        val dataStoreUtil = DataStoreSettings(applicationContext)
        val dt: State<Boolean> = dataStoreUtil.getTheme().collectAsState(initial = false)
        settingsViewModel.setTheme(dt.value)
    }
}
