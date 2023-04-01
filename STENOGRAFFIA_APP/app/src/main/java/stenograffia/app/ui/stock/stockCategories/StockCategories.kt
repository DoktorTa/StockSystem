package stenograffia.app.ui.stock.stockCategories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import stenograffia.app.R
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun StockCategories(navController: NavController){
    // TODO: Кажется создавать тут цвет не оптимально.
    val colorBlackTransparent = Color(red = 0, green = 0, blue = 0, alpha = 0x5f)

    Row() {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(color = colorBlackTransparent)
                .clickable { navController.navigate("ListPaintLine") },
            contentAlignment = Alignment.Center
        ) {
            CategoryText(
                stringResource(R.string.paint_categories_handler),
                stringResource(R.string.paint_categories_description)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(color = colorBlackTransparent),
            contentAlignment = Alignment.Center
        ) {
            CategoryText(
                stringResource(R.string.materials_categories_handler),
                stringResource(R.string.materials_categories_description)
            )
        }
    }
}

@Composable
fun CategoryText(
    handlerText: String = stringResource(R.string.default_text),
    descriptionText: String = stringResource(R.string.default_text)
){
    Column () {
        Text(
            text = handlerText,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = descriptionText,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.stock_categories_padding_text),
                end = dimensionResource(R.dimen.stock_categories_padding_text)
            )
        )
    }
}
