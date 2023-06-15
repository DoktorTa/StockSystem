package stenograffia.app.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import stenograffia.app.R

@Composable
fun HandlerTextString(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.default_text)
) {
    Text(
        text,
        style = MaterialTheme.typography.h1,
        modifier = modifier
            .padding(start = dimensionResource(id = R.dimen.paint_padding_start))
    )
}
