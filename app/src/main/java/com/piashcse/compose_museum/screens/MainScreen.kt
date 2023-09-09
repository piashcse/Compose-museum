package com.piashcse.compose_museum.screens

import android.app.Activity
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piashcse.compose_museum.components.ExitAlertDialog
import com.piashcse.compose_museum.navigation.Navigation
import com.piashcse.compose_museum.navigation.Screen
import com.piashcse.compose_museum.navigation.currentRoute

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val openDialog = remember { mutableStateOf(false) }
    val activity = (LocalContext.current as? Activity)

    BackHandler(enabled = (currentRoute(navController) == Screen.Home.route)) {
        openDialog.value = true
    }
    Scaffold(scaffoldState = scaffoldState, bottomBar = {
        when (currentRoute(navController)) {
           Screen.HomeBottomNavScreen.route, Screen.PopularBottomNavScreen.route, Screen.TopRatedBottomNavScreen.route, Screen.UpComingBottomNavScreen.route -> {
                BottomNavigationUI(navController)
            }
        }
    }) {
        Navigation(
            navController = navController, modifier = Modifier.padding(it), Screen.Home.route
        )
        if (openDialog.value) {
            ExitAlertDialog(navController, {
                openDialog.value = it
            }, {
                activity?.finish()
            })

        }
    }
}

@Composable
fun BottomNavigationUI(navController: NavController) {
    androidx.compose.material.BottomNavigation {
        val items = listOf(
            Screen.HomeNav,
            Screen.PopularNav,
            Screen.TopRatedNav,
            Screen.UpcomingNav,
        )
        items.forEach { item ->
            BottomNavigationItem(label = { Text(text = stringResource(id = item.title)) },
                selected = currentRoute(navController) == item.route,
                icon = item.navIcon,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                })
        }
    }
}