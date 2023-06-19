package stenograffia.app.ui.screens.authScreens.login

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import stenograffia.app.R
import stenograffia.app.ui.navigation.Screens
import stenograffia.app.ui.screens.settings.DataStoreSettings
import stenograffia.app.ui.screens.settings.SettingsViewModel


@Composable
fun Login(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    viewModel: LoginViewModel = hiltViewModel()
){
    val dataStoreAuthTokens = viewModel.dataStoreToken

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
                    text = stringResource(id = R.string.server_disconnect),
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
                label = { Text(stringResource(id = R.string.login_label)) }
            )
            TextField(
                value = passwordText.value,
                onValueChange = {
                    passwordText.value = it
                },
                label = { Text(stringResource(id = R.string.password_label)) }
            )
            Button(
                onClick = {
                    viewModel.singIn(loginText.value, passwordText.value)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text(text = stringResource(id = R.string.sing_in_button_text))
            }
        }

        if (infoText != ""){
            if (loginCorrect){
                LaunchedEffect(true) {
                    dataStoreAuthTokens.saveTokens(viewModel.authTokens!!)
                    settingsViewModel.loadUserByAccessToken()
                    navController.navigate(Screens.StockCategories.route)
                }
            } else {
                Toast.makeText(LocalContext.current, infoText, Toast.LENGTH_LONG).show()
                viewModel.infoText.value = ""
            }
        }
    }
}
