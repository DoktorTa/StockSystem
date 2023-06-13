package stenograffia.app.ui.screens.stockStock.material

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import stenograffia.app.R
import stenograffia.app.ui.screens.stockStock.paint.*
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme

@Composable
fun Material(
    navController: NavController,
    paintId: Int,
    viewModel: MaterialViewModel = hiltViewModel()
) {
    viewModel.loadPaintModelById(paintId)
    ConstraintLayoutContent(viewModel)
}

@Composable
fun MaterialScreen(
    viewModel: MaterialViewModel
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
    ) {
        val (
            typeMaterial, descriptionMaterial, locationMaterial, buttonChangeLocation
        ) = createRefs()

        val showDialogChangeLocation = remember { mutableStateOf(false) }
        val material = viewModel.material

//        if (showDialogChangeLocation.value) {
//            DialogChangeLocation(
//                showDialog = showDialogChangeLocation,
//            )
//        }

        HandlerTextString(
            text = material.typeMaterial,
            modifier = Modifier.constrainAs(typeMaterial) {
                top.linkTo(parent.top)
            }
        )

        TextString(
            text = material.descriptionMaterial,
            modifier = Modifier.constrainAs(descriptionMaterial) {
                top.linkTo(typeMaterial.bottom)
            }
        )

        TextString(
            text = material.locationMaterial,
            modifier = Modifier.constrainAs(locationMaterial) {
                top.linkTo(descriptionMaterial.bottom)
            }
        )

        ButtonShowDialog(
            modifier = Modifier
                .constrainAs(buttonChangeLocation) {
                    top.linkTo(locationMaterial.bottom)
                },
            showDialog = showDialogChangeLocation,
            text_button = stringResource(id = R.string.paint_button_likeness_paint)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    STENOGRAFFIAAPPTheme {
        MaterialScreen()
    }
}

