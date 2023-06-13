package stenograffia.app.ui.screens.stockStock.material

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import stenograffia.app.ui.screens.stockStock.paint.LikenessListPaint
import stenograffia.app.ui.screens.stockStock.paint.PaintViewModel

@Composable
fun DialogChangeLocation(
    showDialog: MutableState<Boolean>,
    navController: NavController,
    viewModel: PaintViewModel
){
    Dialog(onDismissRequest = { showDialog.value = false }) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary)
        ) {
            LikenessListPaint(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}