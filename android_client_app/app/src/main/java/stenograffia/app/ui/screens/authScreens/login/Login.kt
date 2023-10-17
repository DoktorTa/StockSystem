package stenograffia.app.ui.screens.authScreens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import stenograffia.app.R
import stenograffia.app.ui.composables.GenerateSeal
import stenograffia.app.ui.composables.InputDataTextField
import stenograffia.app.ui.navigation.Screens
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

    GenerateSeal(idVectorImage = R.drawable.seal_1)
    GenerateSeal(idVectorImage = R.drawable.seal_2)
    GenerateSeal(idVectorImage = R.drawable.seal_3)
    GenerateSeal(idVectorImage = R.drawable.seal_4)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {

        InputDataTextField(
            textLabel = R.string.login_label,
            value = loginText,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        InputDataTextField(
            textLabel = R.string.password_label,
            value = passwordText,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Box(
            modifier = Modifier
                .padding(
                    bottom = dimensionResource(id = R.dimen.padding_in_auth_screens),
                    top = dimensionResource(id = R.dimen.padding_in_auth_screens)
                )
                .fillMaxWidth()
                .clickable { viewModel.singIn(loginText.value, passwordText.value) }

        ){

            Text(
                text = stringResource(id = R.string.sing_in_button_text),
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(
                        bottom = dimensionResource(
                            R.dimen.padding_bottom_text_button_sing_in_auth_screens
                        )
                    )
            )

            var colorFilter: ColorFilter? = null
            if (isSystemInDarkTheme()){
                val colorMatrix = floatArrayOf(
                    -1f, 0f, 0f, 0f, 255f,
                    0f, -1f, 0f, 0f, 255f,
                    0f, 0f, -1f, 0f, 255f,
                    0f, 0f, 0f, 1f, 0f
                )
                colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
            }


            Image(
                painter = painterResource(R.drawable.sing_in_button),
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        width = dimensionResource(
                            id = R.dimen.wight_image_button_sing_in_auth_screens),
                        height = dimensionResource(
                            id = R.dimen.height_image_button_sing_in_auth_screens)
                    ),
                contentDescription = "",
                colorFilter = colorFilter
            )

        }

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
