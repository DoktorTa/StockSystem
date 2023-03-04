package stenograffia.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme

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
