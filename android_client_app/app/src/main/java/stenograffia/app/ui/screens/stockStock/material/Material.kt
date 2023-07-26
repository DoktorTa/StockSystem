package stenograffia.app.ui.screens.stockStock.material

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import stenograffia.app.R
import stenograffia.app.domain.model.MaterialModel
import stenograffia.app.domain.model.UserRole
import stenograffia.app.ui.composables.ButtonShowDialog
import stenograffia.app.ui.composables.HandlerTextString
import stenograffia.app.ui.composables.TextString
import stenograffia.app.ui.screens.settings.SettingsViewModel
import stenograffia.app.ui.screens.stockStock.paint.*

@Composable
fun Material(
    materialId: Int,
    viewModel: MaterialViewModel = hiltViewModel()
) {
    MaterialScreen(viewModel, materialId)
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MaterialScreen(
    viewModel: MaterialViewModel,
    materialId: Int,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
    ) {

        val (
            typeMaterial, descriptionMaterial, locationMaterial
        ) = createRefs()

        val material: State<MaterialModel> = remember {
            mutableStateOf(viewModel.getMaterial(materialId))
        }

        val infoText = remember { mutableStateOf(viewModel.infoText) }
        if (infoText.value != 0){
            Toast.makeText(LocalContext.current, infoText.value, Toast.LENGTH_SHORT).show()
            infoText.value = 0
        }

        val context = LocalContext.current
        var expanded by remember { mutableStateOf(false) }
        var selectedText by remember { mutableStateOf(material.value.location) }
        val showDialogChangeQuantity = remember { mutableStateOf(false) }

        if (showDialogChangeQuantity.value) {
            DialogChangeQuantity(
                showDialogChangeQuantity,
                mutableStateOf(material.value.id),
                mutableStateOf(material.value.quantityInStorage),
                viewModel)
        }


        HandlerTextString(
            text = material.value.type,
            modifier = Modifier.constrainAs(typeMaterial) {
                top.linkTo(parent.top)
            }
        )

        TextString(
            text = material.value.description,
            modifier = Modifier.constrainAs(descriptionMaterial) {
                top.linkTo(typeMaterial.bottom)
            }
        )

        if (settingsViewModel.getUserStatus()!!.level <= UserRole.STOCK.level) {

            if (material.value.unique){

                Box(
                    modifier = Modifier
                        .constrainAs(locationMaterial) { top.linkTo(descriptionMaterial.bottom) }
                        .padding(start = dimensionResource(id = R.dimen.paint_padding_start))
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        TextField(
                            value = selectedText,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            viewModel.getLocation().forEach { item ->
                                DropdownMenuItem(
                                    content = { Text(text = item) },
                                    onClick = {
                                        selectedText = item
                                        expanded = false
                                        viewModel.changeLocationMaterial(material.value.id, item)
                                        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                    }
                                )
                            }
                        }
                    }
                }
            } else {
                ButtonShowDialog(
                    modifier = Modifier
                        .constrainAs(locationMaterial) { top.linkTo(descriptionMaterial.bottom) }
                        .padding(start = dimensionResource(id = R.dimen.paint_padding_start)),
                    showDialog = showDialogChangeQuantity,
                    text_button = stringResource(id = R.string.paint_button_change_quantity)
                )
            }

        }

    }
}
