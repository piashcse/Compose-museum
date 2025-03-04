package com.piashcse.compose_museum.screens

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piashcse.compose_museum.R
import com.piashcse.compose_museum.components.ExitAlertDialog
import com.piashcse.compose_museum.navigation.Navigation
import com.piashcse.compose_museum.navigation.Screen
import com.piashcse.compose_museum.navigation.currentRoute

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val openDialog = remember { mutableStateOf(false) }
    val activity = LocalActivity.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    BackHandler(enabled = (currentRoute(navController) == Screen.Home.route)) {
        openDialog.value = true
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(
                    stringResource(R.string.home),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                if (currentRoute(navController = navController) != Screen.Home.route) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "arrowBack"
                        )
                    }
                }
            },
            scrollBehavior = scrollBehavior,
        )
    }, bottomBar = {
        when (currentRoute(navController)) {
            Screen.HomeBottomNavScreen.route, Screen.PopularBottomNavScreen.route, Screen.TopRatedBottomNavScreen.route, Screen.UpComingBottomNavScreen.route -> {
                BottomNavigationUI(navController)
            }
        }
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues) // Apply scaffold's top bar padding
                .fillMaxSize()
        ) {
            Navigation(
                navController = navController,
                Screen.Home.route
            )
            if (openDialog.value) {
                ExitAlertDialog(navController, {
                    openDialog.value = it
                }, {
                    activity?.finish()
                })

            }
        }
    })
}

@Composable
fun BottomNavigationUI(navController: NavController) {
    NavigationBar {
        val items = listOf(
            Screen.HomeNav,
            Screen.PopularNav,
            Screen.TopRatedNav,
            Screen.UpcomingNav,
        )
        items.forEach { item ->
            NavigationBarItem(label = { Text(text = stringResource(id = item.title)) },
                selected = currentRoute(navController) == item.route,
                icon = item.navIcon,
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