package stenograffia.app.ui.paint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.ui.res.dimensionResource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import stenograffia.app.R
import stenograffia.app.vw.PaintViewModel


@Composable
fun DialogChangeQuantity(
    showDialogChangeQuantity: MutableState<Boolean>,
    viewModel: PaintViewModel
) {

    val differenceQuantity =  remember { mutableStateOf(0) }
    val quantityOnStock = remember { mutableStateOf(viewModel.paintModel.quantityInStorage) }
    val nothingInStock = remember { mutableStateOf((quantityOnStock.value + differenceQuantity.value) > 0)}

    Dialog(onDismissRequest = { showDialogChangeQuantity.value = false}) {
        Column(modifier = Modifier.background(MaterialTheme.colors.secondary)) {
            InformationText(differenceQuantity, quantityOnStock)
            ChangeButtons(nothingInStock, differenceQuantity, quantityOnStock)
            SeparationBox()
            DialogButton(showDialogChangeQuantity, viewModel, differenceQuantity)
        }
    }
}

@Composable
fun InformationText(
    differenceQuantity: MutableState<Int>,
    onStock: MutableState<Int>
){
    Box(modifier = Modifier.size(
        dimensionResource(R.dimen.width_dialog),
        dimensionResource(R.dimen.side_length_element)
    )) {
        Text(
            text = stringResource(
                R.string.change_quantity_description,
                onStock.value + differenceQuantity.value),
            modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ChangeButtons(
    nothingInStock: MutableState<Boolean>,
    differenceQuantity: MutableState<Int>,
    onStock: MutableState<Int>
){
    Row(modifier = Modifier.background(MaterialTheme.colors.secondary)) {

        Button(
            onClick = {
                nothingInStock.value = (onStock.value - differenceQuantity.value) != 0
                differenceQuantity.value-- },
            shape = RectangleShape,
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.padding_central_button))
                .size(
                    dimensionResource(R.dimen.side_length_element),
                    dimensionResource(R.dimen.side_length_element)
                )
                .background(MaterialTheme.colors.primary),
            enabled = (onStock.value + differenceQuantity.value) > 0
        ) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = null
            )
        }

        Box(modifier = Modifier
            .size(
                dimensionResource(R.dimen.side_length_element),
                dimensionResource(R.dimen.side_length_element)
            )
            .background(MaterialTheme.colors.secondary)) {
            Text(text = "${differenceQuantity.value}", modifier = Modifier.align(Alignment.Center))
        }

        Button(
            onClick = { differenceQuantity.value++ },
            shape = RectangleShape,
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.padding_central_button))
                .size(
                    dimensionResource(R.dimen.side_length_element),
                    dimensionResource(R.dimen.side_length_element)
                )
                .background(MaterialTheme.colors.primary)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }
    }
}

@Composable
fun SeparationBox(){
    Box(modifier = Modifier
        .defaultMinSize(
            dimensionResource(R.dimen.width_dialog),
            dimensionResource(R.dimen.height_separation_box_dialog)
        )
        .background(MaterialTheme.colors.secondary)) {}
}

@Composable
fun DialogButton(
    showDialogChangeQuantity: MutableState<Boolean>,
    viewModel: PaintViewModel,
    differenceQuantity: MutableState<Int>
){
    val modifierButton = Modifier
        .defaultMinSize(
            dimensionResource(R.dimen.width_button_nav),
            dimensionResource(R.dimen.side_length_element)
        )
        .background(MaterialTheme.colors.primary)

    Row {
        Button(
            onClick = { showDialogChangeQuantity.value = false },
            modifier = modifierButton,
            shape = RectangleShape,
        ) {
            Text(text = stringResource(R.string.cancel), color = MaterialTheme.colors.secondary)
        }
        Button(
            onClick = {
                viewModel.changeQuantityPaintInStock(differenceQuantity.value)
                showDialogChangeQuantity.value = false },
            modifier = modifierButton,
            shape = RectangleShape,
        ) {
            Text(text = stringResource(R.string.ok), color = MaterialTheme.colors.secondary)
        }
    }
}