package stenograffia.app.ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun GenerateSeal(idVectorImage: Int){
    val fullRotateDegree: Int = 360
    val offsetCentralShape: Int = 150

    val config = LocalConfiguration.current

    val startPaddingSeal = rememberSaveable {
        mutableStateOf(Random.nextInt(0, config.screenWidthDp - offsetCentralShape))
    }
    val topPaddingSeal = rememberSaveable {
        mutableStateOf(Random.nextInt(0,  config.screenHeightDp - offsetCentralShape))
    }
    val rotationSeal = rememberSaveable {
        mutableStateOf(Random.nextInt(0,  fullRotateDegree))
    }

    val painterSeal = rememberVectorPainter(
        image = ImageVector.vectorResource(id = idVectorImage)
    )

    Canvas(
        modifier = Modifier
            .padding(
                horizontal = startPaddingSeal.value.dp,
                vertical = topPaddingSeal.value.dp
            )
            .rotate(rotationSeal.value.toFloat())
    ) {
        with(painterSeal) {
            draw(painterSeal.intrinsicSize)
        }
    }
}