package stenograffia.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            STENOGRAFFIAAPPTheme {
                StenograffiaApp()
            }
        }
    }
}
