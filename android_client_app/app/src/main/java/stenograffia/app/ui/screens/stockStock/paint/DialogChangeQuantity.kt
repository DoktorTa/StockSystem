package stenograffia.app.ui.screens.stockStock.paint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import stenograffia.app.R
import stenograffia.app.domain.model.PaintModel


@Composable
fun DialogChangeQuantity(
    showDialogChangeQuantity: MutableState<Boolean>,
    paintModel: State<PaintModel>,
    viewModel: PaintViewModel
) {

    val differenceQuantity = remember { mutableStateOf(0) }
    val quantityOnStock = remember { mutableStateOf(paintModel.value.quantityInStorage) }
    val nothingInStock =
        remember { mutableStateOf((quantityOnStock.value + differenceQuantity.value) > 0) }

    Dialog(onDismissRequest = { showDialogChangeQuantity.value = false }) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.secondary)
        ) {
            InformationText(differenceQuantity, quantityOnStock)
            ChangeButtons(nothingInStock, differenceQuantity, quantityOnStock)
            SeparationBox()
            DialogButton(showDialogChangeQuantity, viewModel, differenceQuantity, paintModel.value.id)
        }
    }
}

@Composable
fun InformationText(
    differenceQuantity: MutableState<Int>,
    onStock: MutableState<Int>
) {
    Box(
        modifier = Modifier.size(
            dimensionResource(id = R.dimen.width_dialog),
            dimensionResource(id = R.dimen.side_length_element)
        )
    ) {
        Text(
            text = stringResource(
                R.string.change_quantity_description,
                onStock.value + differenceQuantity.value
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ChangeButtons(
    nothingInStock: MutableState<Boolean>,
    differenceQuantity: MutableState<Int>,
    onStock: MutableState<Int>
) {
    Row(modifier = Modifier.background(MaterialTheme.colors.secondary)) {

        Button(
            onClick = {
                nothingInStock.value = (onStock.value - differenceQuantity.value) != 0
                differenceQuantity.value--
            },
            shape = RectangleShape,
            modifier = Modifier
                .padding(start = dimensionResource(id = R.dimen.padding_central_button))
                .size(
                    dimensionResource(id = R.dimen.side_length_element),
                    dimensionResource(id = R.dimen.side_length_element)
                )
                .background(color = MaterialTheme.colors.primary),
            enabled = (onStock.value + differenceQuantity.value) > 0
        ) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = null
            )
        }

        Box(
            modifier = Modifier
                .size(
                    dimensionResource(id = R.dimen.side_length_element),
                    dimensionResource(id = R.dimen.side_length_element)
                )
                .background(color = MaterialTheme.colors.secondary)
        ) {
            Text(
                text = "${differenceQuantity.value}",
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }

        Button(
            onClick = { differenceQuantity.value++ },
            shape = RectangleShape,
            modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.padding_central_button))
                .size(
                    dimensionResource(id = R.dimen.side_length_element),
                    dimensionResource(id = R.dimen.side_length_element)
                )
                .background(color = MaterialTheme.colors.primary)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }
    }
}

@Composable
fun SeparationBox() {
    Box(
        modifier = Modifier
            .defaultMinSize(
                dimensionResource(id = R.dimen.width_dialog),
                dimensionResource(id = R.dimen.height_separation_box_dialog)
            )
            .background(color = MaterialTheme.colors.secondary)
    ) {}
}

@Composable
fun DialogButton(
    showDialogChangeQuantity: MutableState<Boolean>,
    viewModel: PaintViewModel,
    differenceQuantity: MutableState<Int>,
    paintId: Int
) {
    val modifierButton = Modifier
        .defaultMinSize(
            dimensionResource(id = R.dimen.width_button_nav),
            dimensionResource(id = R.dimen.side_length_element)
        )
        .background(MaterialTheme.colors.primary)

    Row {
        Button(
            onClick = { showDialogChangeQuantity.value = false },
            modifier = modifierButton,
            shape = RectangleShape,
        ) {
            Text(
                text = stringResource(id = R.string.cancel),
                color = MaterialTheme.colors.secondary
            )
        }
        Button(
            onClick = {
                viewModel.changeQuantityPaintInStock(paintId, differenceQuantity.value)
                showDialogChangeQuantity.value = false
            },
            modifier = modifierButton,
            shape = RectangleShape,
        ) {
            Text(
                text = stringResource(id = R.string.ok),
                color = MaterialTheme.colors.secondary
            )
        }
    }
}