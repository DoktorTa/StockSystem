package stenograffia.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import stenograffia.app.InFutureVersion
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.ui.paint.ListPaint
import stenograffia.app.ui.screens.login.Login
import stenograffia.app.ui.screens.settings.Settings
import stenograffia.app.ui.screens.settings.SettingsViewModel
import stenograffia.app.ui.screens.splash.SplashScreen
import stenograffia.app.ui.screens.stock.listPaintLine.ListPaintLine
import stenograffia.app.ui.screens.stock.paint.Paint
import stenograffia.app.ui.screens.stock.stockCategories.StockCategories

@Composable
fun GraphNavigation(
    settingsViewModel: SettingsViewModel
){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { NavigationMenu(navController = navController) }
    ) {
            innerPadding ->
        NavHost(navController, startDestination = Screens.SplashScreen.route, Modifier.padding(innerPadding)) {

            composable(Screens.SplashScreen.route) { SplashScreen(navController) }
            composable(Screens.Login.route) { Login(navController) }

            composable(Screens.StockCategories.route) { StockCategories(navController) }
            composable(Screens.Orders.route) { InFutureVersion(navController) }
            composable(Screens.Objects.route) { InFutureVersion(navController) }
            composable(Screens.Settings.route) { Settings(settingsViewModel) }

            composable(Screens.ListPaintLine.route) { ListPaintLine(navController) }

            composable(Screens.ListPaint.route) { backStackEntry ->
                val nameCreator: String = backStackEntry.arguments?.getString("nameCreator")!!
                val nameLine: String = backStackEntry.arguments?.getString("nameLine")!!
                val paintNameModel = PaintNamesTupleModel(nameCreator = nameCreator, nameLine = nameLine)
                ListPaint(navController, paintNamesTupleModel = paintNameModel)
            }

            composable(Screens.Paint.route) { backStackEntry ->
                val paintId: Int = backStackEntry.arguments?.getString("paintId")!!.toInt()
                Paint(navController, paintId = paintId)
            }

        }
    }
}
