package stenograffia.app.ui.screens.stock.listMaterial

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import stenograffia.app.R
import stenograffia.app.domain.model.MaterialModel


@SuppressLint("RememberReturnType")
@Composable
fun ListMaterialScreen(
    navController: NavController,
    materialListViewModel: ListMaterialViewModel = hiltViewModel(),
) {
    ListMaterial(
        navController = navController,
        materialList = materialListViewModel.materialList
    )
}

@Composable
fun ListMaterial(
    navController: NavController,
    materialList: Flow<List<MaterialModel>>,
) {
    val materialListState by materialList.collectAsStateWithLifecycle(
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
            items(materialListState.size) { index ->
                ListMaterialItem(
                    materialItem = MaterialItem.fromPaintModel(materialListState[index]),
                    modifier = Modifier.clickable {
                        navController.navigate("Material/${materialListState[index].id}")
                    })
            }
        }
    )
}