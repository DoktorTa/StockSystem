package stenograffia.app

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.ui.CustomTopBar
import stenograffia.app.ui.login.Login
import stenograffia.app.ui.stock.listPaintLine.ListPaintLine
import stenograffia.app.ui.paint.ListPaint
import stenograffia.app.ui.settings.Settings
import stenograffia.app.ui.settings.SettingsViewModel
import stenograffia.app.ui.splash.SplashScreen
import stenograffia.app.ui.stock.paint.Paint
import stenograffia.app.ui.stock.stockCategories.StockCategories
import stenograffia.app.ui.stock.listPaint.PaintListViewModel
import stenograffia.app.vw.PaintViewModel


@Composable
fun StenograffiaApp(
    settingsViewModel: SettingsViewModel
){
    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "login") {
//        composable("login") { Login(navController) }
//    }
//    Test(navController = navController, settingsViewModel = settingsViewModel)

    Scaffold(
        bottomBar = { NavigationMenu(navController = navController) }
    ) {
            innerPadding ->
        NavHost(navController, startDestination = Screen.SplashScreen.route, Modifier.padding(innerPadding)) {

            composable(Screen.SplashScreen.route) { SplashScreen(navController) }
            composable(Screen.Login.route) { Login(navController) }

            composable(Screen.StockCategories.route) {StockCategories(navController)}
            composable(Screen.Orders.route) { InFutureVersion(navController) }
            composable(Screen.Objects.route) { InFutureVersion(navController) }
            composable(Screen.Settings.route) { Settings(settingsViewModel) }

            composable("ListPaintLine") { ListPaintLine(navController) }

            composable("PaintList/{nameCreator}/{nameLine}") { backStackEntry ->
                val nameCreator: String = backStackEntry.arguments?.getString("nameCreator")!!
                val nameLine: String = backStackEntry.arguments?.getString("nameLine")!!
                val paintNameModel = PaintNamesTupleModel(nameCreator = nameCreator, nameLine = nameLine)
                ListPaint(navController, paintNamesTupleModel = paintNameModel)
            }

            composable("PAINT/{paintId}") { backStackEntry ->
                val paintId: Int = backStackEntry.arguments?.getString("paintId")!!.toInt()
                Paint(navController, paintId = paintId)
            }

        }
    }
}


sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int?,
    val image: ImageVector?
) {
    object SplashScreen : Screen("SplashScreen", null, null)
    object Login : Screen("Login", null, null)

    object StockCategories : Screen("StockCategories", R.string.stock_menu, Icons.Outlined.Inventory2)
    object Orders : Screen("ORDERS", R.string.orders_menu, Icons.Outlined.CheckBox)
    object Objects : Screen("OBJECTS", R.string.objects_menu, Icons.Outlined.Wallpaper)
    object Settings : Screen("SETTINGS", R.string.settings_menu, Icons.Outlined.Settings)
}

val items = listOf(
    Screen.StockCategories,
    Screen.Orders,
    Screen.Objects,
    Screen.Settings
)

val noBarScreen = listOf(
    Screen.Login,
    Screen.SplashScreen
)

@Composable
fun NavigationMenu(modifier: Modifier = Modifier, navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    if (currentDestination?.route !in noBarScreen.map { item -> item.route }) {

        BottomNavigation(
            modifier = modifier,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen: Screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = screen.image!!,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(stringResource(screen.resourceId!!))
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
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
    } else {
        Spacer(modifier = Modifier.width(0.dp))
    }
}

