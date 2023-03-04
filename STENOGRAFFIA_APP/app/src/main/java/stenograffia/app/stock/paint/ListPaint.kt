package stenograffia.app.stock.paint

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import stenograffia.app.ui.theme.STENOGRAFFIAAPPTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListPaint(modifier: Modifier = Modifier) {
    val vm = ListPaintViewModel()
    LazyVerticalGrid(
        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
        cells = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        content = {
            items(vm.paintList.size) { index ->
                ListPaintItem(PaintItem.fromPaintModel(vm.paintList[index]))
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    STENOGRAFFIAAPPTheme {
        ListPaint()
    }
}