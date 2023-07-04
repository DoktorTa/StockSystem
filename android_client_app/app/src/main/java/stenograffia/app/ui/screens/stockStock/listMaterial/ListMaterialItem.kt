package stenograffia.app.ui.screens.stockStock.listMaterial

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import stenograffia.app.R


@SuppressLint("SuspiciousIndentation")
@Composable
fun ListMaterialItem(
    modifier: Modifier = Modifier,
    materialItem: MaterialItem = MaterialItem()
) {
    val modifierPaddingTextStart = Modifier.padding(
        start = dimensionResource(id = R.dimen.paint_list_item_text_start_padding)
    )

    Column(
        modifier = modifier
            .wrapContentHeight()
            .background(color = MaterialTheme.colors.primary)
    ) {
        Column(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.paint_list_item_height))
                .background(color = materialItem.color)
                .fillMaxWidth()
        ) {
            Text(
                text = materialItem.type,
                color = Color.White,
                modifier = modifierPaddingTextStart,
                style = MaterialTheme.typography.body1,
                maxLines = 1
            )
        }
        Text(
            text = materialItem.location,
            color = materialItem.colorTextLocation,
            style = MaterialTheme.typography.body1,
            modifier = modifierPaddingTextStart
        )
    }
}
