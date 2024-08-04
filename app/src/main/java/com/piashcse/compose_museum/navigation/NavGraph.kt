package com.piashcse.compose_museum.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piashcse.compose_museum.screens.BottomSheetScreen
import com.piashcse.compose_museum.screens.CountryList
import com.piashcse.compose_museum.screens.DateAndTimePicker
import com.piashcse.compose_museum.screens.ExpandableList
import com.piashcse.compose_museum.screens.ExpandableTexView
import com.piashcse.compose_museum.screens.Home
import com.piashcse.compose_museum.screens.HomeBottomNavScreen
import com.piashcse.compose_museum.screens.ImagePicker
import com.piashcse.compose_museum.screens.ImageSlider
import com.piashcse.compose_museum.screens.OnBoarding
import com.piashcse.compose_museum.screens.ParallaxEffect
import com.piashcse.compose_museum.screens.PopularBottomNavScreen
import com.piashcse.compose_museum.screens.TabScreen
import com.piashcse.compose_museum.screens.TopRatedBottomNavScreen
import com.piashcse.compose_museum.screens.UpComingBottomNavScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation(
    navController: NavHostController, modifier: Modifier, initialScreen: String
) {
    NavHost(navController, startDestination = initialScreen) {
        composable(Screen.Home.route) {
            Home(navController = navController)
        }
        composable(Screen.ImageSlider.route) {
            ImageSlider(navController = navController)
        }
        composable(Screen.TabView.route) {
            TabScreen(navController = navController)
        }
        composable(Screen.ExpandableList.route) {
            ExpandableList(navController = navController)
        }
        composable(Screen.ImagePicker.route) {
            ImagePicker(navController = navController)
        }
        composable(Screen.BottomSheet.route) {
            BottomSheetScreen(navController = navController)
        }
        composable(Screen.DateAndTimePicker.route) {
            DateAndTimePicker(navController = navController)
        }
        composable(Screen.OnBoarding.route) {
            OnBoarding(navController = navController)
        }
        composable(Screen.Parallax.route) {
            ParallaxEffect(navController = navController)
        }
        composable(Screen.HomeBottomNavScreen.route) {
            HomeBottomNavScreen(navController = navController)
        }
        composable(Screen.PopularBottomNavScreen.route) {
            PopularBottomNavScreen(navController = navController)
        }
        composable(Screen.TopRatedBottomNavScreen.route) {
            TopRatedBottomNavScreen(navController = navController)
        }
        composable(Screen.UpComingBottomNavScreen.route) {
            UpComingBottomNavScreen(navController = navController)
        }
        composable(Screen.ExpandableText.route) {
            ExpandableTexView(navController = navController)
        }
        composable(Screen.CountryList.route) {
            CountryList()
        }
    }
}
