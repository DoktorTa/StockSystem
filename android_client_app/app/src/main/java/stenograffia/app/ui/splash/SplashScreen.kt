package stenograffia.app.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import stenograffia.app.Screen
import stenograffia.app.domain.model.AuthTokens
import stenograffia.app.ui.login.LoginViewModel
import stenograffia.app.ui.settings.DataStoreSettings

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val dataStoreAuthTokens = DataStoreSettings(LocalContext.current)

    val authToken: Flow<AuthTokens> = dataStoreAuthTokens.getTokens()

    LaunchedEffect(key1 = true){
        val refreshToken: String = authToken.first().refreshToken
        if (refreshToken != ""){
            viewModel.refreshTokens(refreshToken)
            delay(3_000L)

            if (viewModel.authTokens != null){
                dataStoreAuthTokens.saveTokens(viewModel.authTokens!!)
                navController.navigate(Screen.StockCategories.route)
            } else {
                navController.navigate(Screen.Login.route)
            }
        } else {
            navController.navigate(Screen.Login.route)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "Load")
        }
    }

}