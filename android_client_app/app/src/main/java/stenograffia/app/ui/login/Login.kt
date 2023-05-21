package stenograffia.app.ui.login

import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import stenograffia.app.InFutureVersion
import stenograffia.app.NavigationMenu
import stenograffia.app.Screen
import stenograffia.app.StenograffiaApp
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.ui.CustomTopBar
import stenograffia.app.ui.paint.ListPaint
import stenograffia.app.ui.settings.Settings
import stenograffia.app.ui.settings.SettingsViewModel
import stenograffia.app.ui.stock.listPaintLine.ListPaintLine
import stenograffia.app.ui.stock.paint.Paint
import stenograffia.app.ui.stock.stockCategories.StockCategories
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme

@Composable
fun AppSplashScreen(){
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            "CYBER_ANNA",
            style = MaterialTheme.typography.h1,
                modifier = Modifier.align(Alignment.Center)
        )
    }
}

//sealed class ScreenGeneral(val route: String){
//    object Login : ScreenGeneral("login")
//    object General : ScreenGeneral("centralScreen")
//}


@Composable
fun Login(
    settingsViewModel: SettingsViewModel,
    viewModel: LoginViewModel = hiltViewModel()
){
    val loginText = remember { mutableStateOf("") }
    val passwordText = remember { mutableStateOf("") }

    val infoText by viewModel.infoText.observeAsState("")
    val loginCorrect by viewModel.loginCorrect.observeAsState(false)
    val serverConnect by viewModel.serverConnect.observeAsState(true)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            if (!serverConnect){
                Text(
                    text = "SERVER DISCONNECT",
                    style = MaterialTheme.typography.body1,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(CenterHorizontally)
                )
            }
            TextField(
                value = loginText.value,
                onValueChange = {
                    loginText.value = it
                },
                label = { Text("Login") }
            )
            TextField(
                value = passwordText.value,
                onValueChange = {
                    passwordText.value = it
                },
                label = { Text("Password") }
            )
            Button(
                onClick = {
                    viewModel.singIn(loginText.value, passwordText.value)
                    Log.d("LOGIN", "${loginText.value}, ${passwordText.value}")

                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text(text = "SIGN IN")
            }
        }

        if (infoText != ""){
            if (loginCorrect){
                settingsViewModel.loginCorrect.value = true
            } else {
                Toast.makeText(LocalContext.current, infoText, Toast.LENGTH_LONG).show()
                viewModel.infoText.value = ""
            }
        }



    }

}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    STENOGRAFFIAAPPTheme {
//        Login()
//    }
//}
