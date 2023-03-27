package stenograffia.app

import android.app.Activity
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Wallpaper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.ui.listCreator.ListCreator
import stenograffia.app.ui.paint.ListPaint
import stenograffia.app.ui.paint.Paint
import stenograffia.app.ui.paint.customTopBar
import stenograffia.app.ui.stock.central.StockCategories
import stenograffia.app.vw.PaintCreatorViewModel
import stenograffia.app.vw.PaintListViewModel
import stenograffia.app.vw.PaintViewModel


@Composable
fun StenograffiaApp(){
    val navController = rememberNavController()
    val app = ((LocalContext.current as Activity).application as App)

    Scaffold(
        topBar = { customTopBar() },
        bottomBar = { NavigationMenu(navController = navController) }
    ) {
            innerPadding ->
        NavHost(navController, startDestination = Screen.Stock.route, Modifier.padding(innerPadding)) {
            composable(Screen.Stock.route) {StockCategories(navController)}
            composable(Screen.Orders.route) { InFutureVersion(navController) }
            composable(Screen.Objects.route) { InFutureVersion(navController) }
            composable(Screen.Settings.route) { InFutureVersion(navController) }

            composable("PaintListCreator") {
                val viewModel: PaintCreatorViewModel = app.paintComponent.getPaintCreatorViewModel()
                ListCreator(viewModel, navController)
            }

            composable("PaintList/{nameCreator}/{nameLine}") { backStackEntry ->
                // TODO: Антипаттерн, полюбому, нужно продумать навигацию до релиза 0.1.0
                val nameCreator: String = backStackEntry.arguments?.getString("nameCreator")!!
                val nameLine: String = backStackEntry.arguments?.getString("nameLine")!!
                val paintNameModel = PaintNamesTupleModel(nameCreator = nameCreator, nameLine = nameLine)

                val viewModel: PaintListViewModel = app.paintComponent.getPaintListViewModel()
                viewModel.loadPaintList(paintNameModel)
                ListPaint(viewModel, navController)
            }

            composable("PAINT/{paintId}") { backStackEntry ->
                val paintId: Int = backStackEntry.arguments?.getString("paintId")!!.toInt()
                val viewModel: PaintViewModel = app.paintComponent.getPaintViewModel()
                viewModel.loadPaintModelById(paintId)
                Paint(viewModel, navController)
            }

        }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, val image: ImageVector) {
    object Stock : Screen("StockCategories", R.string.stock_menu, Icons.Outlined.Inventory2)
    object Orders : Screen("ORDERS", R.string.orders_menu, Icons.Outlined.CheckBox)
    object Objects : Screen("OBJECTS", R.string.objects_menu, Icons.Outlined.Wallpaper)
    object Settings : Screen("SETTINGS", R.string.settings_menu, Icons.Outlined.Settings)
}

val items = listOf(
    Screen.Stock,
    Screen.Orders,
    Screen.Objects,
    Screen.Settings
)

@Composable
fun NavigationMenu(modifier: Modifier = Modifier, navController: NavController) {
    BottomNavigation(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen: Screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.image,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(screen.resourceId))
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}