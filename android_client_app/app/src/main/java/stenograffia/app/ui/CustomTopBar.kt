package stenograffia.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import stenograffia.app.R

@Composable
fun CustomTopBar(){
    TopAppBar (
        backgroundColor = Color.Black
    ) {
        Text(
            text = "CYBER ANNA",
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.top_bar_padding_text_start)
            ),
            color = Color.White,
            style = MaterialTheme.typography.body2
        )
    }
}
