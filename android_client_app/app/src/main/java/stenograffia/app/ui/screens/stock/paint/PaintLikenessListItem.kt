package stenograffia.app.ui.screens.stock.paint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import stenograffia.app.R
import stenograffia.app.ui.screens.stock.listPaint.PaintItem

@Composable
fun PaintLikenessListItem(
    modifier: Modifier = Modifier,
    paintItem: PaintItem = PaintItem()
) {
    val modifierPaddingTextStart = Modifier.padding(
        start = dimensionResource(id = R.dimen.paint_list_item_text_start_padding)
    )

    Column (
        modifier = modifier
            .wrapContentHeight()
            .background(color = MaterialTheme.colors.primary)) {
        Column (
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.paint_list_item_height))
                .background(color = paintItem.color)
                .fillMaxWidth()) {
            Text(
                text = paintItem.nameColor,
                color = paintItem.colorText,
                modifier = modifierPaddingTextStart,
                style = MaterialTheme.typography.h4,
                maxLines = 1
            )
            Text(
                text = paintItem.codePaint,
                color = paintItem.colorText,
                modifier = modifierPaddingTextStart,
                style = MaterialTheme.typography.h4,
                maxLines = 1
            )
            Text(
                text = stringResource(
                    id = R.string.paint_list_likeness_description_text,
                    paintItem.additionalInformation),
                color = paintItem.colorText,
                modifier = modifierPaddingTextStart,
                maxLines = 1,
                style = MaterialTheme.typography.h4,
                )
        }
        Text(
            text = stringResource(paintItem.statusPaintTextId),
            color = paintItem.colorTextStatus,
            style = MaterialTheme.typography.h4,
            modifier = modifierPaddingTextStart)
    }
}