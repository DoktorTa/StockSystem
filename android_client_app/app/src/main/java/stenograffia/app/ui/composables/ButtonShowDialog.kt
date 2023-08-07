package stenograffia.app.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import stenograffia.app.R

@Composable
fun ButtonShowDialog(
    modifier: Modifier = Modifier,
    showDialog: MutableState<Boolean>,
    text_button: String,
    enabled: Boolean = true
) {
    Button(
        onClick = { showDialog.value = true },
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.secondary
        ),
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
            ),
        enabled = enabled
    ) {
        Text(
            text = text_button,
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders),
                    bottom = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
                )
        )
    }
}