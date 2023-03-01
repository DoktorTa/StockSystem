package stenograffia.app

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.unit.dp
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme

class MainActivity : ComponentActivity() {

    val viewModel: PaintViewModel = PaintViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            STENOGRAFFIAAPPTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = { customTopBar()},
                    bottomBar = { NavigationMenu() }
                ) {
                    ConstraintLayoutContent()
                }
            }
        }
    }
}


@Composable
fun Handler(name: String) {
    RectangleShape
    Text(name)
}

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
            .padding(start = 15.dp)
            .size(255.dp, 1.dp)
            .background(MaterialTheme.colors.primary)
    )
}

@Composable
fun NavigationMenu(modifier: Modifier = Modifier) {
    BottomNavigation(
        modifier = modifier,
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Inventory2,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.stock_menu))
            },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.CheckBox,
                    contentDescription = null)
            },
            label = {
                    Text(stringResource(R.string.orders_menu))
            },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Wallpaper,
                    contentDescription = null)
            },
            label = {
                Text(stringResource(R.string.objects_menu))
            },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.settings_menu))
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun TextString(text: String, modifier: Modifier = Modifier){
    Text(
        text,
        style = MaterialTheme.typography.body1,
        modifier = modifier.padding(start = 15.dp)
    )
}

@Composable
fun HandlerTextString(text: String, modifier: Modifier = Modifier){
    Text(
        text,
        style = MaterialTheme.typography.h1,
        modifier = modifier.padding(start = 15.dp)
    )
}


@Composable
fun ButtonAddToOrder(modifier: Modifier = Modifier){
    Button(
        onClick = {},
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),
        modifier = modifier.padding(start = 15.dp)){
        Text("ADD TO ORDER", modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
    }
}

@Composable
fun ButtonChangeQuantity(modifier: Modifier = Modifier){
    Button(
        onClick = {},
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),
        modifier = modifier.padding(start = 15.dp)){
        Text("CHANGE QUANTITY", modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
    }
}

@Composable
fun ButtonToOrder(modifier: Modifier = Modifier){
    Box(modifier = modifier
        .background(
            color = Color.Black,
            shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 15.dp))){
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.ShoppingBasket,
                contentDescription = null,
                tint = Color.White)
        }
    }
}

@Composable
fun HandlerName(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color.Black, shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 0.dp))
        ) {
        Text(
            text = "MONTANA BLACK",
            color = Color.White,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp).align(Alignment.Center))
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (nameColor, namePaint, separationLine1,
            hexColor, hslColor, cmykColor, separationLine2,
            alikeText, separationLine3,
            onStockText, separationLine4,
            buttonAddToOrder, buttonChangeQuantity,
            colorSquare,
            handlerName, buttonToOrder
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

        ButtonAddToOrder(Modifier
            .constrainAs(buttonAddToOrder) {
                top.linkTo(separationLine4.bottom)
            })

        ButtonChangeQuantity(Modifier
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
        modifier = Modifier.fillMaxWidth().height(48.dp)) {
        val (handlerName, buttonToOrder) = createRefs()

        ButtonToOrder(Modifier
            .constrainAs(buttonToOrder) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            })

        HandlerName(Modifier
            .constrainAs(handlerName) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    STENOGRAFFIAAPPTheme {
        Scaffold(
            topBar = { customTopBar()},
            bottomBar = { NavigationMenu() }
        ) {
            ConstraintLayoutContent()
        }
    }
}