package com.piashcse.compose_museum.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piashcse.compose_museum.screens.*

@Composable
fun Navigation(
    navController: NavHostController, modifier: Modifier, initialScreen: String
) {
    NavHost(navController, startDestination = initialScreen) {
        composable(Screen.Home.route) {
            Home( navController = navController)
        }
        composable(Screen.ImageSlider.route) {
            ImageSlider( navController = navController)
        }
        composable(Screen.TabView.route) {
            TabScreen( navController = navController)
        }
        composable(Screen.ExpandableList.route) {
            ExpandableList( navController = navController)
        }
        composable(Screen.ImageSlider.route) {
            ImagePicker( navController = navController)
        }
    }
}
