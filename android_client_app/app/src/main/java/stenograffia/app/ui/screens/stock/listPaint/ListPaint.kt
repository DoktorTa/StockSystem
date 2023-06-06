package stenograffia.app.ui.paint

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import stenograffia.app.R
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.ui.screens.stock.listPaint.ListPaintItem
import stenograffia.app.ui.screens.stock.listPaint.PaintItem
import stenograffia.app.ui.screens.stock.listPaint.PaintListViewModel


@SuppressLint("RememberReturnType")
@Composable
fun ListPaint(
    navController: NavController,
    paintNamesTupleModel: PaintNamesTupleModel,
    paintListViewModel: PaintListViewModel = hiltViewModel(),
) {

    remember {
        paintListViewModel.loadPaintList(paintNamesTupleModel)
    }

    ListPaint(
        navController = navController,
        paintList = paintListViewModel.paintList
    )
}

@Composable
fun ListPaint(
    navController: NavController,
    paintList: Flow<List<PaintModel>>,
) {
    val paintListState by paintList.collectAsStateWithLifecycle(
        initialValue = listOf()
    )

    LazyVerticalGrid(
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.paint_list_padding),
            end = dimensionResource(id = R.dimen.paint_list_padding)
        ),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.paint_list_item_arrangement)
        ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.paint_list_item_arrangement)
        ),
        content = {
            items(paintListState.size) { index ->
                ListPaintItem(
                    paintItem = PaintItem.fromPaintModel(paintListState[index]),
                    modifier = Modifier.clickable {
                        navController.navigate("PAINT/${paintListState[index].id}")
                    })
            }
        }
    )
}