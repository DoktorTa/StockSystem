package stenograffia.app.ui.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

val items = listOf(
    Screens.StockCategories,
    Screens.Orders,
    Screens.Objects,
    Screens.Settings
)

val noBarScreen = listOf(
    Screens.Login,
    Screens.SplashScreen
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
            items.forEach { screen: Screens ->
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
