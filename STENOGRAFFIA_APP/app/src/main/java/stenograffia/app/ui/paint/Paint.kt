package stenograffia.app.ui.paint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.ColorUtils
import androidx.navigation.NavController
import stenograffia.app.vw.PaintViewModel
import java.lang.Float.max
import kotlin.math.roundToInt


@Composable
fun Paint(viewModel: PaintViewModel, navController: NavController){
    ConstraintLayoutContent(viewModel, navController)
}


val INDENT_START: Dp = 15.dp
val INDENT_END: Dp = 15.dp
val INDENT_TOP: Dp = 5.dp
val INDENT_BOTTOM: Dp = 5.dp

const val DEFAULT_TEXT: String = "Default text"

@Composable
fun ColorSquare(color: Color, modifier: Modifier = Modifier){
    val widthScreen = LocalConfiguration.current.screenWidthDp.dp
    Box(
        modifier = modifier
            .size(widthScreen, widthScreen)
            .background(color)
    )
}

@Composable
fun SeparationLine(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .padding(start = INDENT_START)
            .size(255.dp, 1.dp)
            .background(MaterialTheme.colors.primary)
    )
}

@Composable
fun TextString(text: String = DEFAULT_TEXT, modifier: Modifier = Modifier){
    Text(
        text,
        style = MaterialTheme.typography.body1,
        modifier = modifier.padding(start = INDENT_START)
    )
}

@Composable
fun HandlerTextString(text: String = DEFAULT_TEXT, modifier: Modifier = Modifier){
    Text(
        text,
        style = MaterialTheme.typography.h1,
        modifier = modifier.padding(start = INDENT_START)
    )
}


@Composable
fun ButtonAddToOrder(modifier: Modifier = Modifier){
    Button(
        onClick = {},
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.secondary),
        modifier = modifier.padding(start = INDENT_START)){
        Text("ADD TO ORDER", modifier = Modifier.padding(top = INDENT_TOP, bottom = INDENT_BOTTOM))
    }
}

@Composable
fun ButtonChangeQuantity(showDialogChangeQuantity: MutableState<Boolean>, modifier: Modifier = Modifier){
    Button(
        onClick = {showDialogChangeQuantity.value = true},
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.secondary),
        modifier = modifier.padding(start = INDENT_START)){
        Text("CHANGE QUANTITY", modifier = Modifier.padding(top = INDENT_TOP, bottom = INDENT_BOTTOM))
    }
}


@Composable
fun ButtonShowLikenessPaint(showDialog: MutableState<Boolean>, enabled: Boolean, modifier: Modifier = Modifier){
    Button(
        onClick = {showDialog.value = true},
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.secondary),
        modifier = modifier.padding(start = INDENT_START),
        enabled = enabled
        ){
        Text("LIKENESS PAINT", modifier = Modifier.padding(top = INDENT_TOP, bottom = INDENT_BOTTOM))
    }
}


@Composable
fun ButtonToOrder(modifier: Modifier = Modifier){
    Box(modifier = modifier
        .background(
            color = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 15.dp)
        )){
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.ShoppingBasket,
                contentDescription = null,
                tint = MaterialTheme.colors.secondary)
        }
    }
}

@Composable
fun HandlerName(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 0.dp)
            )
    ) {
        Text(
            text = "MONTANA BLACK",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .padding(start = INDENT_START, end = INDENT_END)
                .align(Alignment.Center))
    }
}

@Composable
fun ConstraintLayoutContent(viewModel: PaintViewModel, navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
    ) {
        val (nameColor, namePaint, separationLine1,
            hexColor, hslColor, cmykColor, separationLine2,
            onStockText, separationLine4,
            buttonAddToOrder, buttonChangeQuantity,
            colorSquare, buttonShowLikenessPaint,
        ) = createRefs()


        val showDialogLikenessPaint =  remember { mutableStateOf(false) }
        val showDialogChangeQuantity =  remember { mutableStateOf(false) }
        val paintModel = viewModel.paintModel

        if(showDialogLikenessPaint.value) {
            DialogLikenessPaint(showDialogLikenessPaint, navController, paintModel.similarColors, viewModel)
        } else if (showDialogChangeQuantity.value) {
            DialogChangeQuantity(showDialogChangeQuantity, viewModel)
        }


        HandlerTextString(paintModel.nameColor,
            Modifier.constrainAs(nameColor) {
                top.linkTo(parent.top)
            })

        TextString(paintModel.codePaint,
            Modifier.constrainAs(namePaint) {
                top.linkTo(nameColor.bottom)
            })
        SeparationLine(
            Modifier.constrainAs(separationLine1){
                top.linkTo(namePaint.bottom)
            })


        TextString("HEX: ${colorToHex(paintModel.color)}",
            Modifier.constrainAs(hexColor) {
                top.linkTo(separationLine1.bottom)
            })
        TextString("HSL: ${colorToHsl(paintModel.color)}",
            Modifier.constrainAs(hslColor) {
                top.linkTo(hexColor.bottom)
            })
        TextString("CMYK: ${colorToCmyk(paintModel.color)}",
            Modifier.constrainAs(cmykColor) {
                top.linkTo(hslColor.bottom)
            })
        SeparationLine(
            Modifier.constrainAs(separationLine2){
                top.linkTo(cmykColor.bottom)
            })

        TextString("ON STOCK: ${paintModel.quantityInStorage} PIECES",
            Modifier.constrainAs(onStockText) {
                top.linkTo(separationLine2.bottom)
            })
        SeparationLine(
            Modifier.constrainAs(separationLine4){
                top.linkTo(onStockText.bottom)
            })

        ButtonShowLikenessPaint(showDialogLikenessPaint,
            paintModel.similarColors.isNotEmpty(),
            Modifier
                .constrainAs(buttonShowLikenessPaint) {
                    top.linkTo(separationLine4.bottom)
                }
        )

//        ButtonAddToOrder(
//            Modifier
//            .constrainAs(buttonAddToOrder) {
//                top.linkTo(buttonShowLikenessPaint.bottom)
//            })
//
        ButtonChangeQuantity(showDialogChangeQuantity,
            Modifier
            .constrainAs(buttonChangeQuantity) {
                top.linkTo(buttonShowLikenessPaint.bottom)
            })



        ColorSquare(color = Color(0xFF000000 + paintModel.color),
            Modifier.constrainAs(colorSquare){
                bottom.linkTo(parent.bottom)
            }
        )
    }
}


@Composable
private fun PaintTextButton(modifier: Modifier = Modifier){
    TextButton(onClick = { /*TODO*/ }) {
        
    }

}

private fun getSimilarColor(viewModel: PaintViewModel): String{
    var similarColor = ""


//    sim.forEach {
//        similarColor += " " + viewModel.(it)!!.codePaint
//    }

    return similarColor
}

private fun colorToHsl(color: Int): String{
    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(color, hsl)

    val hue = hsl[0].roundToInt()
    val saturation = (hsl[1] * 100).roundToInt()
    val lightness = (hsl[2] * 100).roundToInt()

    return "hsl($hue, $saturation, $lightness)"
}

private fun colorToCmyk(colorInt: Int): String {
    val color = Color(colorInt)
    val r = color.red
    val g = color.green
    val b = color.blue

    val r1 = r / 255f
    val g1 = g / 255f
    val b1 = b / 255f

    val m = listOf<Float>(r, g, b).maxOrNull()!!

    val k = 1.0f - max(r1, max(g1, b1))

    val cyan = if (k == 1f) 0f else (1.0f - r1 - k) / (1.0f - k)
    val magenta = if (k == 1f) 0f else (1.0f - g1 - k) / (1.0f - k)
    val yellow = if (k == 1f) 0f else (1.0f - b1 - k) / (1.0f - k)

    val kInt = ((1.0f - m) * 100).roundToInt()
    val cyanInt = (cyan * 100).roundToInt()
    val magentaInt = (magenta * 100).roundToInt()
    val yellowInt = (yellow * 100).roundToInt()

    return "cmyk($cyanInt, $magentaInt, $yellowInt, $kInt)"
}

private fun colorToHex(colorInt: Int): String{
    val hex = Integer.toHexString(colorInt)
    val hexString = hex.padStart(6, '0')
    return "#$hexString"
}

@Composable
fun customTopBar(){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Black)) {
        val (handlerName, buttonToOrder) = createRefs()

        ButtonToOrder(
            Modifier
            .constrainAs(buttonToOrder) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            })

        HandlerName(
            Modifier
            .constrainAs(handlerName) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//
//    STENOGRAFFIAAPPTheme {
//        Scaffold(
//            topBar = { customTopBar()},
//            bottomBar = { NavigationMenu() }
//        ) {
//            ConstraintLayoutContent()
//        }
//    }
//}