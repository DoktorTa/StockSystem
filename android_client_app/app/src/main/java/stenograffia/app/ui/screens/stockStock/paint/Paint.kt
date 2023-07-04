package stenograffia.app.ui.screens.stockStock.paint

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import stenograffia.app.R
import stenograffia.app.domain.model.UserRole
import stenograffia.app.ui.composables.ButtonShowDialog
import stenograffia.app.ui.composables.HandlerTextString
import stenograffia.app.ui.composables.SeparationLine
import stenograffia.app.ui.composables.TextString
import stenograffia.app.ui.screens.settings.SettingsViewModel
import kotlin.math.roundToInt


@Composable
fun Paint(
    navController: NavController,
    paintId: Int,
    viewModel: PaintViewModel = hiltViewModel()
) {
    ConstraintLayoutContent(viewModel, navController, paintId)
}

@Composable
fun ConstraintLayoutContent(
    viewModel: PaintViewModel,
    navController: NavController,
    paintId: Int,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
    ) {
        val (
            nameColor, namePaint, separationLine1,
            hexColor, hslColor, cmykColor, separationLine2,
            onStockText, separationLine4, buttonChangeQuantity,
            colorSquare, buttonShowLikenessPaint,
        ) = createRefs()

        val showDialogLikenessPaint = remember { mutableStateOf(false) }
        val showDialogChangeQuantity = remember { mutableStateOf(false) }
        val paintModel = remember { mutableStateOf(viewModel.loadPaintModelById(paintId = paintId)) }

        if (showDialogLikenessPaint.value) {
            DialogLikenessPaint(
                showDialog = showDialogLikenessPaint,
                navController = navController,
                viewModel = viewModel,
                paintModel = paintModel
            )
        } else if (showDialogChangeQuantity.value) {
            DialogChangeQuantity(showDialogChangeQuantity, paintModel, viewModel)
        }

        val infoText = remember { mutableStateOf(viewModel.infoText) }
        if (infoText.value != 0){
            Toast.makeText(LocalContext.current, infoText.value, Toast.LENGTH_SHORT).show()
            infoText.value = 0
        }

        HandlerTextString(
            text = paintModel.value.nameColor,
            modifier = Modifier.constrainAs(nameColor) {
                top.linkTo(parent.top)
            })

        TextString(
            text = paintModel.value.codePaint,
            modifier = Modifier.constrainAs(namePaint) {
                top.linkTo(nameColor.bottom)
            })

        SeparationLine(
            Modifier.constrainAs(separationLine1) {
                top.linkTo(namePaint.bottom)
            })

        TextString(
            text = stringResource(
                id = R.string.paint_hex_color, colorToHex(paintModel.value.color)
            ),
            modifier = Modifier.constrainAs(hexColor) {
                top.linkTo(separationLine1.bottom)
            })

        TextString(
            text = stringResource(
                id = R.string.paint_hsl_color, colorToHsl(paintModel.value.color)
            ),
            modifier = Modifier.constrainAs(hslColor) {
                top.linkTo(hexColor.bottom)
            })

        TextString(
            text = stringResource(
                id = R.string.paint_cmyk_color, colorToCmyk(paintModel.value.color)
            ),
            modifier = Modifier.constrainAs(cmykColor) {
                top.linkTo(hslColor.bottom)
            })

        SeparationLine(
            Modifier.constrainAs(separationLine2) {
                top.linkTo(cmykColor.bottom)
            })

        TextString(
            text = stringResource(
                id = R.string.paint_quantity_in_stock, paintModel.value.quantityInStorage
            ),
            modifier = Modifier.constrainAs(onStockText) {
                top.linkTo(separationLine2.bottom)
            })

        SeparationLine(
            Modifier.constrainAs(separationLine4) {
                top.linkTo(onStockText.bottom)
            })

        ButtonShowDialog(
            modifier = Modifier
                .constrainAs(buttonShowLikenessPaint) {
                    top.linkTo(separationLine4.bottom)
                },
            showDialog = showDialogLikenessPaint,
            text_button = stringResource(id = R.string.paint_button_likeness_paint),
            enabled = paintModel.value.similarColors.isNotEmpty(),
        )

        if (settingsViewModel.getUserStatus()!!.level <= UserRole.STOCK.level) {
            ButtonShowDialog(
                modifier = Modifier
                    .constrainAs(buttonChangeQuantity) {
                        top.linkTo(buttonShowLikenessPaint.bottom)
                    },
                showDialog = showDialogChangeQuantity,
                text_button = stringResource(id = R.string.paint_button_change_quantity)
            )
        }


        ColorSquare(color = Color(0xFF000000 + paintModel.value.color),
            Modifier.constrainAs(colorSquare) {
                bottom.linkTo(parent.bottom)
            }
        )
    }
}

@Composable
fun ColorSquare(
    color: Color,
    modifier: Modifier = Modifier
) {
    var widthScreen = LocalConfiguration.current.screenWidthDp.dp
    val heightScreen = LocalConfiguration.current.screenHeightDp.dp

    if (widthScreen * 2 >= heightScreen){
        widthScreen /= 2
    }

    Box(
        modifier = modifier
            .size(
                width = widthScreen,
                height = widthScreen
            )
            .background(color = color)
    )
}

private fun colorToHsl(color: Int): String {
    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(color, hsl)

    val hue = hsl[0].roundToInt()
    val saturation = (hsl[1] * 100).roundToInt()
    val lightness = (hsl[2] * 100).roundToInt()

    return "$hue, $saturation, $lightness"
}

private fun colorToCmyk(colorInt: Int): String {
    val color = Color(colorInt)
    val r = color.red
    val g = color.green
    val b = color.blue

    val r1 = r / 255f
    val g1 = g / 255f
    val b1 = b / 255f

    val m = listOf(r, g, b).maxOrNull()!!

    val k = 1.0f - java.lang.Float.max(r1, java.lang.Float.max(g1, b1))

    val cyan = if (k == 1f) 0f else (1.0f - r1 - k) / (1.0f - k)
    val magenta = if (k == 1f) 0f else (1.0f - g1 - k) / (1.0f - k)
    val yellow = if (k == 1f) 0f else (1.0f - b1 - k) / (1.0f - k)

    val kInt = ((1.0f - m) * 100).roundToInt()
    val cyanInt = (cyan * 100).roundToInt()
    val magentaInt = (magenta * 100).roundToInt()
    val yellowInt = (yellow * 100).roundToInt()

    return "$cyanInt, $magentaInt, $yellowInt, $kInt"
}

private fun colorToHex(colorInt: Int): String {
    val hex = Integer.toHexString(colorInt)
    return hex.padStart(6, '0')
}