package stenograffia.app.stock.paint

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import stenograffia.app.App
import stenograffia.app.domain.model.PaintModel
import javax.inject.Inject


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListPaint(navController: NavController, modifier: Modifier = Modifier) {
    val app = ((LocalContext.current as Activity).application as App)

    val vm = app.paintComponent.getPaintUseCase()
    val paintList: List<PaintModel> = vm.getLinePaint("BLACK", "MONTANA")
    LazyVerticalGrid(
        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
        cells = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        content = {
            items(paintList.size) { index ->
                ListPaintItem(PaintItem.fromPaintModel(paintList[index]),
                    Modifier.clickable {
                        navController.navigate("PAINT/${paintList[index].id}") })
            }
        }
    )
}

//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//
//    STENOGRAFFIAAPPTheme {
//        ListPaint()
//    }
//}