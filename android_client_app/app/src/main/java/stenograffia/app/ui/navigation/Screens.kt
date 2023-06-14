package stenograffia.app.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Wallpaper
import androidx.compose.ui.graphics.vector.ImageVector
import stenograffia.app.R

sealed class Screens(
    val route: String,
    @StringRes val resourceId: Int?,
    val image: ImageVector?
) {
//    Screen start
    object SplashScreen : Screens("SplashScreen", null, null)
    object Login : Screens("Login", null, null)

//    Screen stock
    object Paint : Screens("Paint/{paintId}", null, null)
    object ListPaintLine : Screens("ListPaintLine", null, null)
    object ListPaint : Screens("PaintList/{nameCreator}/{nameLine}", null, null)
    object Material : Screens("Material/{materialId}", null, null)
    object MaterialsList : Screens("MaterialList", null, null)

    object StockCategories : Screens("StockCategories", R.string.stock_menu, Icons.Outlined.Inventory2)
    object Orders : Screens("ORDERS", R.string.orders_menu, Icons.Outlined.CheckBox)
    object Objects : Screens("OBJECTS", R.string.objects_menu, Icons.Outlined.Wallpaper)
    object Settings : Screens("SETTINGS", R.string.settings_menu, Icons.Outlined.Settings)
}
