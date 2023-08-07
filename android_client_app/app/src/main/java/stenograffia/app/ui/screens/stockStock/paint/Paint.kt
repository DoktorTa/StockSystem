package stenograffia.app.ui.screens.stockStock.paint

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import stenograffia.app.R
import stenograffia.app.domain.model.PaintType
import stenograffia.app.models.PaintModelUi
import stenograffia.app.models.toPaintModelUi
import kotlin.math.roundToInt


@Composable
fun Paint(
    navController: NavController,
    paintId: Int,
    viewModel: PaintViewModel = hiltViewModel()
) {

    val paintModel = viewModel.getPaintModelById(paintId)

    PaintScreen(
        navController = navController,
        copyPaintInfo = {viewModel.copyPaintInfo()},
        paintModelUi = paintModel.toPaintModelUi()
    )
}


@SuppressLint("UnrememberedMutableState")
@Composable
fun PaintScreen(
    navController: NavController,
    copyPaintInfo: (PaintModelUi) -> Unit = {},
    paintModelUi: PaintModelUi
) {

    val menuVision = remember { mutableStateOf(false) }
    val dialogChangeQuantity = remember { mutableStateOf(false) }

    // TODO: ЧИНИ
//    if (dialogChangeQuantity.value) {
//        DialogChangeQuantity(
//            showDialogChangeQuantity = dialogChangeQuantity,
//            elementId = mutableStateOf(paintModelUi.id),
//            quantityOnStock = mutableStateOf(paintModelUi.quantityInStorage),
//            viewModel = paintViewModel
//        )
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF000000 + paintModelUi.color))
    ) {

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PaintInfo(
                    paintModelUi = paintModelUi
                )
                CopyButtons(
                    copyPaintInfo = copyPaintInfo,
                    paintModelUi = paintModelUi
                )
            }

            ColorText(
                paintModelUi = paintModelUi
            )
            StockLine(
                paintModelUi = paintModelUi,
                dialogChangeQuantity = dialogChangeQuantity
            )

            Text(
                text = paintModelUi.nameColor,
                modifier = Modifier
                    .padding(start = dimensionResource(
                        id = R.dimen.paint_screen_padding_from_screen_borders
                    )),
                color = paintModelUi.contrastTextOnColor,
                style = MaterialTheme.typography.h1
            )

            PaintButton(
                paintModelUi = paintModelUi,
                menuVision = menuVision)
        }
    }
}

@Composable
fun PaintInfo(
    paintModelUi: PaintModelUi
) {
    Column {
        Text(
            text = paintModelUi.codePaint,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders),
                    top = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
                ),
            color = paintModelUi.contrastTextOnColor,
            style = MaterialTheme.typography.body1,
        )
        Text(
            text = paintModelUi.nameCreator,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
                ),
            color = paintModelUi.contrastTextOnColor,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = paintModelUi.nameLine,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
                ),
            color = paintModelUi.contrastTextOnColor,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = stringResource(id = R.string.paint_screen_type_label, paintModelUi.type),
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
                ),
            color = paintModelUi.contrastTextOnColor,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun CopyButtons(
    copyPaintInfo: (PaintModelUi) -> Unit = {},
    paintModelUi: PaintModelUi
) {
    Column(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders),
                end = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
            )
    ) {
        IconButton(onClick = { copyPaintInfo(paintModelUi) }) {
            Icon(
                imageVector = Icons.Default.ContentCopy,
                contentDescription = null,
                tint = paintModelUi.contrastTextOnColor,
                modifier = Modifier
                    .size(
                        width = dimensionResource(id = R.dimen.paint_screen_icon_size),
                        height = dimensionResource(id = R.dimen.paint_screen_icon_size)
                    )
            )
        }
    }
}

@Composable
fun ColorText(
    paintModelUi: PaintModelUi
) {

    Row(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
            )
    ) {
        Text(
            text = stringResource(id = R.string.paint_screen_color_label),
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
                )
                .width(width = dimensionResource(id = R.dimen.paint_screen_width_label)),
            color = paintModelUi.contrastTextOnColor,
            style = MaterialTheme.typography.body1
        )

        Column {
            Text(
                text = stringResource(
                    id = R.string.paint_screen_color_hex_label,
                    paintModelUi.color.colorToHex()
                ),
                modifier = Modifier
                    .padding(start = dimensionResource(
                        id = R.dimen.paint_screen_start_padding_values)
                    ),
                color = paintModelUi.contrastTextOnColor,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = stringResource(
                    id = R.string.paint_screen_color_hsl_label,
                    paintModelUi.color.colorToHsl()
                ),
                modifier = Modifier
                    .padding(start = dimensionResource(
                        id = R.dimen.paint_screen_start_padding_values)
                    ),
                color = paintModelUi.contrastTextOnColor,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = stringResource(
                    id = R.string.paint_screen_color_cmyk_label,
                    Color(paintModelUi.color).colorToCmyk()
                ),
                modifier = Modifier
                    .padding(start = dimensionResource(
                        id = R.dimen.paint_screen_start_padding_values)
                    ),
                color = paintModelUi.contrastTextOnColor,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = stringResource(
                    id = R.string.paint_screen_color_uv_resistance,
                    paintModelUi.uv_resistance
                ),
                modifier = Modifier
                    .padding(start = dimensionResource(
                        id = R.dimen.paint_screen_start_padding_values)
                    ),
                color = paintModelUi.contrastTextOnColor,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun StockLine(
    paintModelUi: PaintModelUi,
    dialogChangeQuantity: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(
                text = stringResource(id = R.string.paint_screen_quantity_in_stock_label),
                modifier = Modifier
                    .padding(
                        start = dimensionResource(
                            id = R.dimen.paint_screen_padding_from_screen_borders
                        )
                    )
                    .width(width = dimensionResource(id = R.dimen.paint_screen_width_label)),
                color = paintModelUi.contrastTextOnColor,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = stringResource(
                    id = R.string.paint_screen_quantity_in_stock_value,
                    paintModelUi.quantityInStorage
                ),
                modifier = Modifier
                    .padding(start = dimensionResource(
                        id = R.dimen.paint_screen_start_padding_values)
                    ),
                color = paintModelUi.contrastTextOnColor,
                style = MaterialTheme.typography.body1
            )
        }

        CustomTextButton(
            text = stringResource(id = R.string.paint_screen_button_change_quantity),
            colorButton = paintModelUi.contrastTextOnColor,
            onClick = { dialogChangeQuantity.value = !dialogChangeQuantity.value },
            modifier = Modifier
                .padding(
                    end = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
                ),
        )
    }
}

@Composable
fun CustomTextButton(
    text: String,
    colorButton: Color,
    modifier: Modifier,
    onClick: () -> Unit
){
    TextButton(
        shape = CutCornerShape(5.dp),
        border = BorderStroke(2.dp, colorButton),
        modifier = modifier.width(125.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            color = colorButton,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun PaintButton(
    paintModelUi: PaintModelUi,
    menuVision: MutableState<Boolean>,
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders),
                bottom = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
            )
            .fillMaxHeight()
    ) {

        Crossfade(
            targetState = menuVision.value,
        ) { isChecked ->
            if (isChecked) {
                MenuButton(paintModelUi)
            }
        }

        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {

            IconButton(onClick = { menuVision.value = !menuVision.value }) {

                val rotation by animateFloatAsState(
                    targetValue = if (menuVision.value) 180f else 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing),
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer { rotationX = rotation },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        tint = paintModelUi.contrastTextOnColor,
                        modifier = Modifier
                            .size(
                                width = dimensionResource(id = R.dimen.paint_screen_icon_size),
                                height = dimensionResource(id = R.dimen.paint_screen_icon_size)
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun MenuButton(paintModelUi: PaintModelUi){
    Column(modifier = Modifier.fillMaxWidth()) {

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            CustomTextButton(
                text = stringResource(id = R.string.paint_screen_button_colors),
                colorButton = paintModelUi.contrastTextOnColor,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(
                        end = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
                    )
                    .weight(1f),
            )
            CustomTextButton(
                text = stringResource(id = R.string.paint_screen_button_likeness),
                colorButton = paintModelUi.contrastTextOnColor,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(
                        end = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
                    )
                    .weight(1f),
            )
            CustomTextButton(
                text = stringResource(id = R.string.paint_screen_button_gradient),
                colorButton = paintModelUi.contrastTextOnColor,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(
                        end = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
                    )
                    .weight(1f),
            )
        }

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomTextButton(
                text = stringResource(id = R.string.paint_screen_button_add_to_order),
                colorButton = paintModelUi.contrastTextOnColor,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(
                        end = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
                    )
                    .weight(2f),
            )
            CustomTextButton(
                text = stringResource(id = R.string.paint_screen_button_buy),
                colorButton = paintModelUi.contrastTextOnColor,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(
                        end = dimensionResource(id = R.dimen.paint_screen_padding_from_screen_borders)
                    )
                    .weight(1f),
            )
        }
    }
}

private fun Int.colorToHsl(): String {
    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(this, hsl)

    val hue = hsl[0].roundToInt()
    val saturation = (hsl[1] * 100).roundToInt()
    val lightness = (hsl[2] * 100).roundToInt()

    return "$hue, $saturation, $lightness"
}

private fun Color.colorToCmyk(): String {
    val r1 = this.red / 255f
    val g1 = this.green / 255f
    val b1 = this.blue / 255f

    val m = listOf(this.red, this.green, this.blue).maxOrNull()!!

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

private fun Int.colorToHex(): String {
    val hex = Integer.toHexString(this)
    return hex.padStart(6, '0')
}

@Preview
@Composable
fun SimpleComposablePreview() {
    PaintScreen(
        navController = rememberNavController(),
        copyPaintInfo = {},
        paintModelUi = PaintModelUi(
            id = 1,
            type = PaintType.CANS_DEFAULT,
            timeLabel = 10,
            nameCreator = "MONTANA CANS",
            nameLine = "BLK - 400ml",
            codePaint = "BLK - 1242",
            nameColor = "POTATO",
            descriptionColor = "",
            color = 0xff11f3,
            quantityInStorage = 1,
            similarColors = listOf(listOf()),
            possibleToBuy = true,
        )
    )
}

