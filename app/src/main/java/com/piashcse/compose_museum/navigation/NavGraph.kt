package com.piashcse.compose_museum.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piashcse.compose_museum.screens.*

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
        composable(Screen.ImagePicker.route) {
            ImagePicker( navController = navController)
        }
        composable(Screen.BottomSheet.route) {
            BottomSheetScreen( navController = navController)
        }
        composable(Screen.DateAndTimePicker.route) {
            DateAndTimePicker( navController = navController)
        }
        composable(Screen.OnBoarding.route) {
            OnBoarding( navController = navController)
        }
        composable(Screen.Parallax.route) {
            ParallaxEffect( navController = navController)
        }
    }
}
