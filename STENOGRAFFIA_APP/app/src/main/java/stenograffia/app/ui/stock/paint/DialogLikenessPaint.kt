package stenograffia.app.ui.stock.paint

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import stenograffia.app.App
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.ui.paint.ListPaint
import stenograffia.app.ui.stock.listPaint.PaintItem
import stenograffia.app.vw.PaintViewModel

@Composable
fun DialogLikenessPaint(
    showDialog: MutableState<Boolean>,
    navController: NavController,
    likenessList: List<List<Int>>,
    viewModel: PaintViewModel
){
    Dialog(onDismissRequest = { showDialog.value = false }) {
        Box(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = 50.dp,
                    minHeight = 50.dp
                )
                .background(MaterialTheme.colors.secondary)
        ) {
            LikenessListPaint(
                likenessList = likenessList,
                navController = navController
            )
        }
    }
}

// TODO: Подумать чтобы likenessList внутри содержал словарь а не лист
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LikenessListPaint(likenessList: List<List<Int>>, navController: NavController) {
    val app = LocalContext.current.applicationContext as App

//    val vm = app.paintComponent.getPaintUseCase()
    val paintList: MutableList<PaintModel> = mutableListOf()
    val percentLikenessList: MutableList<Int> = mutableListOf()

//    likenessList.forEach {
//        paintList.add(vm.getPaintModelById(it[0])!!)
//        percentLikenessList.add(it[1])
//    }

    ListPaint(
        navController = navController,
        paintList = paintList
    )

    LazyVerticalGrid(
        modifier = Modifier.padding(start = 3.dp, end = 3.dp, top = 3.dp, bottom = 3.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        content = {
            items(paintList.size) { index ->
                LikenessListPaintItem(
                    PaintItem.fromPaintModel(paintList[index], additionalInformation = percentLikenessList[index].toString()),
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
        Text(
            text = stringResource(paintItem.statusPaintTextId),
            color = paintItem.colorTextStatus,
            style = MaterialTheme.typography.body1,
            modifier = modifier)
    }
}
