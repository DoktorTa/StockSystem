package stenograffia.app.ui.screens.stockStock.material

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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import stenograffia.app.R
import stenograffia.app.domain.model.MaterialModel
import stenograffia.app.ui.composables.HandlerTextString
import stenograffia.app.ui.composables.TextString
import stenograffia.app.ui.screens.stockStock.paint.*

@Composable
fun Material(
    materialId: Int,
    viewModel: MaterialViewModel = hiltViewModel()
) {
    MaterialScreen(viewModel, materialId)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MaterialScreen(
    viewModel: MaterialViewModel,
    materialId: Int
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

        val context = LocalContext.current
        var expanded by remember { mutableStateOf(false) }
        var selectedText by remember { mutableStateOf(material.value.location) }

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

    }
}
