package stenograffia.app.ui.paint

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import stenograffia.app.R
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.ui.stock.listPaint.PaintItem
import stenograffia.app.ui.stock.listPaint.PaintListViewModel
import stenograffia.app.ui.stock.listPaintLine.PaintLineViewModel


@SuppressLint("RememberReturnType")
@Composable
fun ListPaint(
    navController: NavController,
    paintNamesTupleModel: PaintNamesTupleModel,
    paintListViewModel: PaintListViewModel = viewModel(),
) {



    remember {
        paintListViewModel.loadPaintList(paintNamesTupleModel)
    }

    val paintList by paintListViewModel.paintList.collectAsStateWithLifecycle(
        initialValue = listOf()
    )

    LazyVerticalGrid(
        modifier = Modifier.padding(
            start = dimensionResource(R.dimen.paint_list_padding),
            end = dimensionResource(R.dimen.paint_list_padding)
        ),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.paint_list_item_arrangement)
        ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.paint_list_item_arrangement)
        ),
        content = {
            items(paintList.size) { index ->
                ListPaintItem(
                    paintItem = PaintItem.fromPaintModel(paintList[index]),
                    modifier = Modifier.clickable {
                        navController.navigate("PAINT/${paintList[index].id}") })
            }
        }
    )
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun ListPaintItem(
    modifier: Modifier = Modifier,
    paintItem: PaintItem = PaintItem()
) {
    val modifierPaddingTextStart = Modifier.padding(
        start = dimensionResource(R.dimen.paint_list_item_text_start_padding)
    )

    Column (
        modifier = modifier
            .wrapContentHeight()
            .background(color = MaterialTheme.colors.primary)) {
        Column (
            modifier = Modifier
                .height(dimensionResource(R.dimen.paint_list_item_height))
                .background(color = paintItem.color)
                .fillMaxWidth()) {
            Text(
                text = paintItem.nameColor,
                color = paintItem.colorText,
                modifier = modifierPaddingTextStart,
                style = MaterialTheme.typography.body1,
                maxLines = 1
            )
            Text(
                text = paintItem.codePaint,
                color = paintItem.colorText,
                modifier = modifierPaddingTextStart,
                style = MaterialTheme.typography.body1,
                maxLines = 1
            )
        }
        Text(
            text = stringResource(paintItem.statusPaintTextId),
            color = paintItem.colorTextStatus,
            style = MaterialTheme.typography.body1,
            modifier = modifierPaddingTextStart
        )
    }
}
