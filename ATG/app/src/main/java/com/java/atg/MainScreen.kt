package com.java.atg

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.primarySurface
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var globalConf: GlobalConf = GlobalConf(LocalContext.current)
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            BottomNavGraph(navController = navController, globalConf = globalConf)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val menus = listOf(
        BottomBarMenu.Process,
        BottomBarMenu.Memory,
        BottomBarMenu.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = MaterialTheme.colorScheme.primaryContainer) {
        menus.forEach { menu: BottomBarMenu ->
            AddItem(
                rowScope = this,
                screen = menu,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun AddItem(
    rowScope: RowScope,
    screen: BottomBarMenu,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    rowScope.BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = screen.iconId),
                contentDescription = "Navigation Icon"
            )

        },

        selected = currentDestination?.hierarchy?.any { navDest: NavDestination ->
            navDest.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                // pop every destination up to the starter destination from backstack
                // before we navigate
                // https://developer.android.com/jetpack/compose/navigation#nav-to-composable
                this.popUpTo(navController.graph.findStartDestination().id) {
                    this.inclusive = true
                }
                // Navigate to the destination only if we’re not already on
                // the destination to avoid multiple copies in backstack
                this.launchSingleTop = true
            }
        }
    )
}













