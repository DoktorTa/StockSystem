package stenograffia.app.ui.stock.listPaintLine

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import stenograffia.app.R
import stenograffia.app.di.daggerViewModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme

@Composable
fun ListPaintLine(
    navController: NavController,
    viewModelPaintLine: PaintLineViewModel = daggerViewModel()
){

    val creatorListStable by viewModelPaintLine.allPaintName.collectAsStateWithLifecycle(
        initialValue = listOf()
    )

    LazyColumn(
        modifier = Modifier
            .padding(
                start = dimensionResource(R.dimen.paint_line_list_padding),
                end = dimensionResource(R.dimen.paint_line_list_padding),
                top = dimensionResource(R.dimen.paint_line_list_padding_top)
            ),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.paint_line_list_vertical_arrangement)
        ),
        content = {
            items(creatorListStable.size) { index ->
                ListCreatorItem(
                    textItem = creatorListStable[index].toString(),
                    modifier = Modifier.clickable {
                        val paintName: PaintNamesTupleModel = creatorListStable[index]
                        navController.navigate(
                            "PaintList/${paintName.nameCreator}/${paintName.nameLine}")})
            }
        }
    )
}

@Composable
fun ListCreatorItem(
    modifier: Modifier = Modifier,
    textItem: String = stringResource(R.string.default_text)
){
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colors.primary)
            .fillMaxWidth()
    ) {
        Text(
            text = textItem,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.paint_line_item_padding),
                top = dimensionResource(R.dimen.paint_line_item_padding),
                bottom = dimensionResource(R.dimen.paint_line_item_padding)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    STENOGRAFFIAAPPTheme {
        ListCreatorItem()
    }
}
