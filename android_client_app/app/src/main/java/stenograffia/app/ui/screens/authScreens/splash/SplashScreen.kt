package stenograffia.app.ui.screens.authScreens.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import stenograffia.app.R
import stenograffia.app.ui.navigation.Screens
import stenograffia.app.domain.model.AuthTokens
import stenograffia.app.ui.composables.GenerateSeal
import stenograffia.app.ui.screens.authScreens.login.LoginViewModel
import stenograffia.app.ui.screens.settings.SettingsViewModel

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val dataStoreAuthTokens = viewModel.dataStoreToken

    val authToken: Flow<AuthTokens> = dataStoreAuthTokens.getTokens()

    LaunchedEffect(key1 = true){
        val refreshToken: String = authToken.first().refreshToken
        if (refreshToken != ""){
            viewModel.refreshTokens(refreshToken)
            delay(1_500L)

            if (viewModel.authTokens != null){
                settingsViewModel.loadUserByAccessToken()
                dataStoreAuthTokens.saveTokens(viewModel.authTokens!!)
                navController.navigate(Screens.StockCategories.route)
            } else {
                navController.navigate(Screens.Login.route)
            }
        } else {
            navController.navigate(Screens.Login.route)
        }
    }

    GenerateSeal(idVectorImage = R.drawable.seal_1)
    GenerateSeal(idVectorImage = R.drawable.seal_2)
    GenerateSeal(idVectorImage = R.drawable.seal_3)
    GenerateSeal(idVectorImage = R.drawable.seal_4)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {


        Text(
            text = stringResource(id = R.string.connecting_to_the_server),
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(id = R.string.default_serial_number),
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(
                    bottom = dimensionResource(id = R.dimen.padding_bottom_auth_screen)
                )
                .fillMaxWidth()
        )
    }
}