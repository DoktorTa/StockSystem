package stenograffia.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme

@Composable
fun Paint(navController: NavController){
    ConstraintLayoutContent()
}


val INDENT_START: Dp = 15.dp
val INDENT_END: Dp = 15.dp
val INDENT_TOP: Dp = 5.dp
val INDENT_BOTTOM: Dp = 5.dp

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
fun TextString(text: String, modifier: Modifier = Modifier){
    Text(
        text,
        style = MaterialTheme.typography.body1,
        modifier = modifier.padding(start = INDENT_START)
    )
}

@Composable
fun HandlerTextString(text: String, modifier: Modifier = Modifier){
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
fun ButtonChangeQuantity(modifier: Modifier = Modifier){
    Button(
        onClick = {},
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.secondary),
        modifier = modifier.padding(start = INDENT_START)){
        Text("CHANGE QUANTITY", modifier = Modifier.padding(top = INDENT_TOP, bottom = INDENT_BOTTOM))
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
            modifier = Modifier.padding(start = INDENT_START, end = INDENT_END).align(Alignment.Center))
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.secondary)
    ) {
        val (nameColor, namePaint, separationLine1,
            hexColor, hslColor, cmykColor, separationLine2,
            alikeText, separationLine3,
            onStockText, separationLine4,
            buttonAddToOrder, buttonChangeQuantity,
            colorSquare,
        ) = createRefs()

        HandlerTextString("IVORY",
            Modifier.constrainAs(nameColor) {
                top.linkTo(parent.top)
            })

        TextString("BLK-8000-400",
            Modifier.constrainAs(namePaint) {
                top.linkTo(nameColor.bottom)
            })
        SeparationLine(
            Modifier.constrainAs(separationLine1){
                top.linkTo(namePaint.bottom)
            })


        TextString("HEX: #F2E8C4",
            Modifier.constrainAs(hexColor) {
                top.linkTo(separationLine1.bottom)
            })
        TextString("HSL: hsl(47, 64, 86)",
            Modifier.constrainAs(hslColor) {
                top.linkTo(hexColor.bottom)
            })
        TextString("CMYK: cmyk(0, 4, 19, 5)",
            Modifier.constrainAs(cmykColor) {
                top.linkTo(hslColor.bottom)
            })
        SeparationLine(
            Modifier.constrainAs(separationLine2){
                top.linkTo(cmykColor.bottom)
            })

        TextString("ALIKE: [MTN94 RV-188]",
            Modifier.constrainAs(alikeText) {
                top.linkTo(separationLine2.bottom)
            })
        SeparationLine(
            Modifier.constrainAs(separationLine3){
                top.linkTo(alikeText.bottom)
            })

        TextString("ON STOCK: 12 PIECES",
            Modifier.constrainAs(onStockText) {
                top.linkTo(separationLine3.bottom)
            })
        SeparationLine(
            Modifier.constrainAs(separationLine4){
                top.linkTo(onStockText.bottom)
            })

        ButtonAddToOrder(
            Modifier
            .constrainAs(buttonAddToOrder) {
                top.linkTo(separationLine4.bottom)
            })

        ButtonChangeQuantity(
            Modifier
            .constrainAs(buttonChangeQuantity) {
                start.linkTo(buttonAddToOrder.end)
                top.linkTo(separationLine4.bottom)
            })

        ColorSquare(color = Color(0xFFF2E8C4),
            Modifier.constrainAs(colorSquare){
                bottom.linkTo(parent.bottom)
            }
        )
    }
}

@Composable
fun customTopBar(){
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth().height(48.dp).background(Color.Black)) {
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