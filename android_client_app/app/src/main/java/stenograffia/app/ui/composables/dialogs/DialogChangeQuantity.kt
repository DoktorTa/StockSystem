package stenograffia.app.ui.composables.dialogs


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import stenograffia.app.R
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme
import stenograffia.app.utils.IDiffQuantity


@Composable
fun DialogChangeQuantity(
    showDialogChangeQuantity: MutableState<Boolean>,
    elementId: State<Int>,
    quantityOnStock: MutableState<Int>,
    viewModel: IDiffQuantity
) {
    val differenceQuantity = remember { mutableIntStateOf(0) }

    DialogChangeQuantityBox(
        showDialogChangeQuantity = showDialogChangeQuantity,
        differenceQuantity = differenceQuantity,
        quantityOnStock = quantityOnStock,
        onClickOk = { viewModel.changeQuantity(elementId.value, differenceQuantity.intValue) }
    )
}


@Composable
fun DialogChangeQuantityBox(
    showDialogChangeQuantity: MutableState<Boolean>,
    differenceQuantity: MutableState<Int>,
    quantityOnStock: MutableState<Int>,
    onClickOk: () -> Unit
) {

    Dialog(
        onDismissRequest = { showDialogChangeQuantity.value = false }
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.secondary)
        ) {
            InformationText(
                text = stringResource(
                    R.string.change_quantity_description,
                    quantityOnStock.value + differenceQuantity.value
                ),
                wight = dimensionResource(id = R.dimen.dialog_width)
            )

            ChangeButtons(differenceQuantity, quantityOnStock)

            SelectionButtons(
                onClickOk = {
                    onClickOk()
                    showDialogChangeQuantity.value = false
                },
                onClickCancel = { showDialogChangeQuantity.value = false },
                minWidthScreen = dimensionResource(id = R.dimen.dialog_width_button_nav)
            )
        }
    }
}


@Composable
fun ChangeButtons(
    differenceQuantity: MutableState<Int>,
    onStock: MutableState<Int>
) {

    Row(
        modifier = Modifier.background(MaterialTheme.colors.secondary)
    ) {

        ButtonChangeQuantity(
            text = "-6",
            onClick = {
                differenceQuantity.value -= 6
                      },
            enabled = (onStock.value + differenceQuantity.value) >= 6,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.dialog_quantity_change_padding_elements),
                    end = dimensionResource(id = R.dimen.dialog_quantity_change_padding_elements),
                ).testTag("ButtonMinus6")
        )

        ButtonChangeQuantity(
            text = "-1",
            onClick = {
                differenceQuantity.value -= 1
            },
            enabled = (onStock.value + differenceQuantity.value) >= 1,
            modifier = Modifier.testTag("ButtonMinus1")
        )

        Box(
            modifier = Modifier
                .size(
                    dimensionResource(id = R.dimen.dialog_quantity_change_height_central_elements),
                    dimensionResource(id = R.dimen.dialog_quantity_change_height_central_elements)
                )
                .background(color = MaterialTheme.colors.secondary)
        ) {
            BasicTextField(
                value = "${differenceQuantity.value}",
                onValueChange = { newText ->
                    try {
                        if (newText == "-"){
                            differenceQuantity.value = -1
                        }
                        differenceQuantity.value = newText.toInt()
                    } catch (e: NumberFormatException) {
                        differenceQuantity.value = 0
                    }
                },
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag("TextDifferenceQuantity"),
                textStyle = MaterialTheme.typography.body1.merge(
                    TextStyle(
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Center
                    )
                ),
            )
        }

        ButtonChangeQuantity(
            text = "+1",
            onClick = {
                differenceQuantity.value += 1
            },
            modifier = Modifier.testTag("ButtonPlus1")
        )

        ButtonChangeQuantity(
            text = "+6",
            onClick = {
                differenceQuantity.value += 6
            },
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.dialog_quantity_change_padding_elements),
                    end = dimensionResource(id = R.dimen.dialog_quantity_change_padding_elements),
                ).testTag("ButtonPlus6")
        )
    }
}

@Composable
fun ButtonChangeQuantity(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    OutlinedButton(
        shape = CutCornerShape(
            dimensionResource(id = R.dimen.dialog_quantity_change_cut_corner_button)
        ),
        border = BorderStroke(
            dimensionResource(id = R.dimen.dialog_quantity_change_border_button),
            MaterialTheme.colors.primary
        ),
        modifier = modifier
            .size(
                dimensionResource(id = R.dimen.dialog_quantity_change_height_central_elements),
                dimensionResource(id = R.dimen.dialog_quantity_change_height_central_elements)
            ),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.primary,
            disabledBackgroundColor = Color.LightGray,
            disabledContentColor = Color.DarkGray
        ),
        contentPadding = PaddingValues(
            dimensionResource(id = R.dimen.dialog_quantity_change_padding_button_content),
            dimensionResource(id = R.dimen.dialog_quantity_change_padding_button_content)
        )
    ) {
        Text(
            text = text,
            maxLines = 1,
            style = MaterialTheme.typography.body1
        )
    }
}


@Preview
@Composable
fun Preview(){
    STENOGRAFFIAAPPTheme(
        darkTheme = false
    ) {
        val differenceQuantity = remember { mutableIntStateOf(0) }
        val quantityOnStock = remember { mutableIntStateOf(0) }
        val showDialogChangeQuantity = remember { mutableStateOf(true) }

        DialogChangeQuantityBox(
            showDialogChangeQuantity = showDialogChangeQuantity,
            differenceQuantity = differenceQuantity,
            quantityOnStock = quantityOnStock,
            onClickOk = { /* TODO */ }
        )
    }
}

