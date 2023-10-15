package stenograffia.app.ui.composables.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import stenograffia.app.R

@Composable
fun SelectionButtons(
    onClickOk: () -> Unit,
    onClickCancel: () -> Unit,
    minWidthScreen: Dp,
) {
    val modifierButton = Modifier
        .defaultMinSize(
            minWidth = minWidthScreen,
            minHeight = dimensionResource(id = R.dimen.dialog_height_selection_buttons_block)
        )
        .background(MaterialTheme.colors.primary)

    Column {

        Box (
            modifier = Modifier
                .defaultMinSize(
                    minWidth = minWidthScreen,
                    dimensionResource(id = R.dimen.dialog_height_separation_box)
                )
                .background(color = MaterialTheme.colors.secondary)
        )

        Row {
            Button(
                onClick = onClickCancel,
                modifier = modifierButton
                    .testTag("CancelClick"),
                shape = RectangleShape,
            ) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.body1
                )
            }

            Button(
                onClick = onClickOk,
                modifier = modifierButton
                    .testTag("OkClick"),
                shape = RectangleShape,
            ) {
                Text(
                    text = stringResource(id = R.string.ok),
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}