package stenograffia.app.ui.screens.stock.listPaintLine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import stenograffia.app.R


@Composable
fun ListCreatorItem(
    modifier: Modifier = Modifier,
    textItem: String = stringResource(id = R.string.default_text)
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colors.primary)
            .fillMaxWidth()
    ) {
        Text(
            text = textItem,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.paint_line_item_padding),
                top = dimensionResource(id = R.dimen.paint_line_item_padding),
                bottom = dimensionResource(id = R.dimen.paint_line_item_padding)
            )
        )
    }
}