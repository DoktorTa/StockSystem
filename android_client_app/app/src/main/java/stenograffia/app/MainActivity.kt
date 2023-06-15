package stenograffia.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import stenograffia.app.ui.navigation.GraphNavigation
import stenograffia.app.ui.screens.settings.DataStoreSettings
import stenograffia.app.ui.screens.settings.SettingsViewModel
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var dataStoreSettings: DataStoreSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStoreSettings = DataStoreSettings(applicationContext)

        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            LoadSettings(settingsViewModel)
            LoadLocate(settingsViewModel)

            STENOGRAFFIAAPPTheme(
                darkTheme = settingsViewModel.isDarkThemeEnabled.value
            ) {
                GraphNavigation(settingsViewModel = settingsViewModel)
            }
        }
    }

    @Composable
    private fun LoadSettings(settingsViewModel: SettingsViewModel){
        val theme: State<Boolean> = dataStoreSettings.getTheme().collectAsState(initial = false)
        settingsViewModel.setTheme(theme.value)
    }

    @Composable
    private fun LoadLocate(settingsViewModel: SettingsViewModel){
        val locate: State<String> = dataStoreSettings.getLocale().collectAsState(
            initial = Locale.ENGLISH.language
        )
        settingsViewModel.setLocale(Locale(locate.value))

        val configuration = baseContext.resources.configuration
        configuration.setLocale(settingsViewModel.localeActive.value)
        baseContext.resources.updateConfiguration(configuration, null)

    }
}
