package stenograffia.app.ui.screens.stockStock.paint

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import stenograffia.app.R
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.ui.screens.stockStock.listPaint.PaintItem

@Composable
fun DialogLikenessPaint(
    showDialog: MutableState<Boolean>,
    navController: NavController,
    viewModel: PaintViewModel,
    paintModel: State<PaintModel>
){
    Dialog(onDismissRequest = { showDialog.value = false }) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary)
        ) {
            LikenessListPaint(
                viewModel = viewModel,
                navController = navController,
                paintModel = paintModel
            )
        }
    }
}

@Composable
fun LikenessListPaint(
    viewModel: PaintViewModel,
    navController: NavController,
    paintModel: State<PaintModel>
) {
    val (paintList, percentLikenessList) = viewModel.getLikenessPaintList(paintModel.value)

    PaintLikenessList(
        navController = navController,
        paintList = paintList,
        paintListLikeness = percentLikenessList
    )
}

@Composable
fun PaintLikenessList(
    navController: NavController,
    paintList: List<PaintModel>,
    paintListLikeness: Map<Int, String>
){
    LazyVerticalGrid(
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.paint_list_likeness_paint),
            end = dimensionResource(id = R.dimen.paint_list_likeness_paint),
            top = dimensionResource(id = R.dimen.paint_list_likeness_paint),
            bottom = dimensionResource(id = R.dimen.paint_list_likeness_paint)),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.paint_list_item_arrangement)
        ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.paint_list_item_arrangement)
        ),
        content = {
            items(paintList.size) { index ->
                val paintItem: PaintModel = paintList[index]
                PaintLikenessListItem(
                    paintItem = PaintItem.fromPaintModel(
                        paintItem,
                        additionalInformation = paintListLikeness[paintItem.id]!!
                    ),
                    modifier = Modifier.clickable {
                        navController.navigate("PAINT/${paintItem.id}") })
            }
        }
    )
}
