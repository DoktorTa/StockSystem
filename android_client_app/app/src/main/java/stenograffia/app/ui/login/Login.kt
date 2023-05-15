package stenograffia.app.ui.login

import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
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

@Composable
fun Login(
    viewModel: LoginViewModel = hiltViewModel()
){
    val loginText = remember { mutableStateOf("") }
    val passwordText = remember { mutableStateOf("") }

    val intertetConnection = remember { mutableStateOf(true) }
    val infoText = remember { mutableStateOf(viewModel.infoText) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            if (!intertetConnection.value){
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
                enabled = intertetConnection.value
            ) {
                Text(text = "SIGN IN")
            }
        }

        if (infoText.value != ""){
            Toast.makeText(LocalContext.current, infoText.value, Toast.LENGTH_LONG).show()
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
