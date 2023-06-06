package stenograffia.app.ui.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import stenograffia.app.ui.navigation.Screens
import stenograffia.app.ui.screens.settings.DataStoreSettings


@Composable
fun Login(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
){
    val dataStoreAuthTokens = DataStoreSettings(LocalContext.current)

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
                LaunchedEffect(true) {
                    dataStoreAuthTokens.saveTokens(viewModel.authTokens!!)
                    navController.navigate(Screens.StockCategories.route)
                }
            } else {
                Toast.makeText(LocalContext.current, infoText, Toast.LENGTH_LONG).show()
                viewModel.infoText.value = ""
            }
        }
    }
}
