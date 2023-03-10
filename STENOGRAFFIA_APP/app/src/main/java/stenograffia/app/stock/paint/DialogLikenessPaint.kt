package stenograffia.app.stock.paint

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import stenograffia.app.App
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme

@Composable
fun DialogLikenessPaint(showDialog: MutableState<Boolean>, navController: NavController, likenessList: List<List<Int>>){
    Dialog(onDismissRequest = { showDialog.value = false }) {
        Box(
            modifier = Modifier
                .defaultMinSize(50.dp, 50.dp)
                .background(MaterialTheme.colors.secondary)
        ) {
            LikenessListPaint(likenessList = likenessList, navController = navController)
        }
    }
}

// TODO: Подумать чтобы likenessList внутри содержал словарь а не лист
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LikenessListPaint(likenessList: List<List<Int>>, navController: NavController, modifier: Modifier = Modifier) {
    Log.d("STARAR", "ads1")

    val app = LocalContext.current.applicationContext as App
    Log.d("STARAR", "ads2")

    val vm = app.paintComponent.getPaintUseCase()
    val paintList: MutableList<PaintModel> = mutableListOf()
    val percentLikenessList: MutableList<Int> = mutableListOf()

    likenessList.forEach {
        paintList.add(vm.getPaintModelById(it[0])!!)
        percentLikenessList.add(it[1])
    }


    LazyVerticalGrid(
        modifier = Modifier.padding(start = 3.dp, end = 3.dp, top = 3.dp, bottom = 3.dp),
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        content = {
            items(paintList.size) { index ->
                LikenessListPaintItem(PaintItem.fromPaintModel(paintList[index], additionalInformation = percentLikenessList[index].toString()),
                    Modifier.clickable {
                        navController.navigate("PAINT/${paintList[index].id}") })
            }
        }
    )
}

@Composable
fun LikenessListPaintItem(paintItem: PaintItem, modifier: Modifier=Modifier, width: Int = 90) {
    val modifier1 = Modifier.padding(start = 2.dp)

    Column (
        modifier = modifier
            .wrapContentHeight()
            .background(color = MaterialTheme.colors.primary)) {
        Column (
            modifier = Modifier
                .height(60.dp)
                .background(color = paintItem.color)
                .fillMaxWidth()) {
            Text(text = paintItem.nameColor, color = paintItem.colorText, modifier = modifier1, maxLines = 1)
            Text(text = paintItem.codePaint, color = paintItem.colorText, modifier = modifier1, maxLines = 1)
            Text(text = "LIKENESS: ${paintItem.additionalInformation}", color = paintItem.colorText, modifier = modifier1, maxLines = 1)
        }
        Text(text = paintItem.statusPaint, color = paintItem.colorTextStatus, style = MaterialTheme.typography.body1, modifier = modifier)
    }
}

//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//
//    STENOGRAFFIAAPPTheme {
//        LikenessListPaintItem()
//    }
//}
