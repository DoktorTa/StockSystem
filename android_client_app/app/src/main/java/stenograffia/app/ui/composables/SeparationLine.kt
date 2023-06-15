package stenograffia.app.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import stenograffia.app.R

@Composable
fun SeparationLine(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(start = dimensionResource(id = R.dimen.paint_padding_start))
            .size(
                width = 255.dp,
                height = 1.dp
            )
            .background(color = MaterialTheme.colors.primary)
    )
}