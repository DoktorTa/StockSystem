package stenograffia.app.ui.stock.paint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import stenograffia.app.R
import stenograffia.app.vw.PaintViewModel
import kotlin.math.roundToInt


@Composable
fun Paint(
    navController: NavController,
    viewModel: PaintViewModel = hiltViewModel()
) {
    ConstraintLayoutContent(viewModel, navController)
}


@Composable
fun ConstraintLayoutContent(viewModel: PaintViewModel, navController: NavController) {
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
        val paintModel = viewModel.paintModel

        if (showDialogLikenessPaint.value) {
            DialogLikenessPaint(
                showDialogLikenessPaint,
                navController,
                paintModel.similarColors,
                viewModel
            )
        } else if (showDialogChangeQuantity.value) {
            DialogChangeQuantity(showDialogChangeQuantity, viewModel)
        }


        HandlerTextString(
            text = paintModel.nameColor,
            modifier = Modifier.constrainAs(nameColor) {
                top.linkTo(parent.top)
            })

        TextString(
            text = paintModel.codePaint,
            modifier = Modifier.constrainAs(namePaint) {
                top.linkTo(nameColor.bottom)
            })

        SeparationLine(
            Modifier.constrainAs(separationLine1) {
                top.linkTo(namePaint.bottom)
            })

        TextString(
            text = stringResource(
                id = R.string.paint_hex_color, colorToHex(paintModel.color)
            ),
            modifier = Modifier.constrainAs(hexColor) {
                top.linkTo(separationLine1.bottom)
            })

        TextString(
            text = stringResource(
                id = R.string.paint_hsl_color, colorToHsl(paintModel.color)
            ),
            modifier = Modifier.constrainAs(hslColor) {
                top.linkTo(hexColor.bottom)
            })

        TextString(
            text = stringResource(
                id = R.string.paint_cmyk_color, colorToCmyk(paintModel.color)
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
                id = R.string.paint_quantity_in_stock, paintModel.quantityInStorage
            ),
            modifier = Modifier.constrainAs(onStockText) {
                top.linkTo(separationLine2.bottom)
            })

        SeparationLine(
            Modifier.constrainAs(separationLine4) {
                top.linkTo(onStockText.bottom)
            })

        ButtonShowLikenessPaint(
            showDialogLikenessPaint,
            paintModel.similarColors.isNotEmpty(),
            Modifier
                .constrainAs(buttonShowLikenessPaint) {
                    top.linkTo(separationLine4.bottom)
                }
        )

        ButtonChangeQuantity(
            showDialogChangeQuantity,
            Modifier
                .constrainAs(buttonChangeQuantity) {
                    top.linkTo(buttonShowLikenessPaint.bottom)
                })

        ColorSquare(color = Color(0xFF000000 + paintModel.color),
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
    val widthScreen = LocalConfiguration.current.screenWidthDp.dp
    Box(
        modifier = modifier
            .size(
                width = widthScreen,
                height = widthScreen
            )
            .background(color = color)
    )
}

@Composable
fun SeparationLine(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(start = dimensionResource(id = R.dimen.paint_padding_start))
            .size(
                width = 255.dp,
                height = 1.dp
            )
            .background(color = MaterialTheme.colors.primary)
    )
}

@Composable
fun TextString(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.default_text)
) {
    Text(
        text,
        style = MaterialTheme.typography.body1,
        modifier = modifier
            .padding(start = dimensionResource(id = R.dimen.paint_padding_start))
    )
}

@Composable
fun HandlerTextString(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.default_text)
) {
    Text(
        text,
        style = MaterialTheme.typography.h1,
        modifier = modifier
            .padding(start = dimensionResource(id = R.dimen.paint_padding_start))
    )
}

@Composable
fun ButtonChangeQuantity(
    showDialogChangeQuantity: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { showDialogChangeQuantity.value = true },
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.secondary
        ),
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.paint_padding_start)
            )
    ) {
        Text(
            text = stringResource(id = R.string.paint_button_change_quantity),
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.paint_padding_top),
                    bottom = dimensionResource(id = R.dimen.paint_padding_bottom)
                )
        )
    }
}

@Composable
fun ButtonShowLikenessPaint(
    showDialog: MutableState<Boolean>,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { showDialog.value = true },
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.secondary
        ),
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.paint_padding_start)
            ),
        enabled = enabled
    ) {
        Text(
            text = stringResource(id = R.string.paint_button_likeness_paint),
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.paint_padding_top),
                    bottom = dimensionResource(id = R.dimen.paint_padding_bottom)
                )
        )
    }
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