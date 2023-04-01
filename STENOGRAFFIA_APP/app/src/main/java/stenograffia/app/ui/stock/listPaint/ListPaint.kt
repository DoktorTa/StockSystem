package stenograffia.app.ui.paint

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import stenograffia.app.R
import stenograffia.app.di.daggerViewModel
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.ui.stock.listPaint.PaintItem
import stenograffia.app.ui.stock.listPaintLine.PaintLineViewModel
import stenograffia.app.ui.stock.listPaint.PaintListViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListPaint(
    paintListViewModel: PaintListViewModel,
    navController: NavController,
    viewModelPaintLine: PaintLineViewModel = daggerViewModel()
) {

    val paintList: List<PaintModel>? = paintListViewModel.paintList
    
    val creatorListStable by viewModelPaintLine.allPaintName.collectAsStateWithLifecycle(
        initialValue = listOf()
    )

    if (paintList == null){
        Text(text = "ERROR")
    } else {
        LazyVerticalGrid(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
            cells = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalArrangement = Arrangement.spacedBy(3.dp),
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
                maxLines = 1
            )
            Text(
                text = paintItem.codePaint,
                color = paintItem.colorText,
                modifier = modifierPaddingTextStart,
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
