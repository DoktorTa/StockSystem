package stenograffia.app.ui.composables


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import stenograffia.app.R


@Composable
fun InputDataTextField(
    textLabel: Int,
    value: MutableState<String>,
    modifier: Modifier) {

    val onBackgroundColors = Color.Black

    BasicTextField(
            value = value.value,
            onValueChange = {
                value.value = it
            },
            textStyle = MaterialTheme.typography.h3,
            modifier = modifier,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .size(
                            width = dimensionResource(id = R.dimen.width_input_auth_screen),
                            height = dimensionResource(id = R.dimen.height_input_auth_screen)
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = stringResource(id = textLabel),
                            color = Color.Black,
                            style = MaterialTheme.typography.body1,
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        innerTextField()

                        Canvas(
                            modifier = Modifier.fillMaxWidth()
                        ){
                            val canvasWidth = size.width
                            val canvasHeight = size.height

                            drawLine(
                                start = Offset(x = canvasWidth, y = canvasHeight),
                                end = Offset(x = 0f, y = canvasHeight),
                                color = onBackgroundColors,
                            )
                        }
                    }
                }
            }
        )
}

