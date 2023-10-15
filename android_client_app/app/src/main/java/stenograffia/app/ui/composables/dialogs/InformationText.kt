package stenograffia.app.ui.composables.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import stenograffia.app.R

@Composable
fun InformationText(
    text: String,
    wight: Dp
) {
    Box(
        modifier = Modifier.width(wight)
            .defaultMinSize(
                minHeight = dimensionResource(id = R.dimen.dialog_height_information_block),
//                minHeight = 52.dp,
                minWidth = wight
            )
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.body1
                .merge(
                    TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
        )
    }
}