package stenograffia.app.ui.stock.listPaintLine

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import stenograffia.app.R
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme

@Composable
fun ListPaintLine(
    navController: NavController,
    viewModelPaintLine: PaintLineViewModel = hiltViewModel()
) {
    ListPaintLine(
        navController = navController,
        paintLine = viewModelPaintLine.allPaintName
    )
}

@Composable
fun ListPaintLine(
    paintLine: Flow<List<PaintNamesTupleModel>>,
    navController: NavController,
) {
    val creatorListStable by paintLine.collectAsStateWithLifecycle(
        initialValue = listOf()
    )

    LazyColumn(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.paint_line_list_padding),
                end = dimensionResource(id = R.dimen.paint_line_list_padding),
                top = dimensionResource(id = R.dimen.paint_line_list_padding_top)
            ),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.paint_line_list_vertical_arrangement)
        ),
        content = {
            items(creatorListStable.size) { index ->
                ListCreatorItem(
                    textItem = creatorListStable[index].toString(),
                    modifier = Modifier.clickable {
                        val paintName: PaintNamesTupleModel = creatorListStable[index]
                        navController.navigate(
                            "PaintList/${paintName.nameCreator}/${paintName.nameLine}"
                        )
                    })
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    STENOGRAFFIAAPPTheme {
        ListCreatorItem()
    }
}
