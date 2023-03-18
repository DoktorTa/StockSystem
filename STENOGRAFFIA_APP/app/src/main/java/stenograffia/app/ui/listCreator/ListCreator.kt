package stenograffia.app.ui.listCreator

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme
import stenograffia.app.vw.PaintCreatorViewModel

@Composable
fun ListCreator(viewModel: PaintCreatorViewModel, navController: NavController){
    val creatorList: List<PaintNamesTupleModel> = viewModel.getAllPaintName()
    LazyColumn(
        modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 5.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        content = {
            items(creatorList.size) { index ->
                ListCreatorItem(
                    creatorList[index].toString(),
                    Modifier.clickable {
                        val paintName: PaintNamesTupleModel = creatorList[index]
                        navController.navigate("PaintList/${paintName.nameCreator}/${paintName.nameLine}") })
            }
        }
    )
}

@Composable
fun ListCreatorItem(
    textItem: String = "Default Text",
    modifier: Modifier = Modifier
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
            modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
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
