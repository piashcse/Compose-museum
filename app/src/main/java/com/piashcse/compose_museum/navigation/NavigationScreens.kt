package com.piashcse.compose_museum.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.piashcse.compose_museum.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_name,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
    val objectName: String = "",
    val objectPath: String = ""
) {
    data object Home : Screen("home")
    data object ImageSlider : Screen("image_slider")
    data object TabView : Screen("tab_view")
    data object ExpandableList : Screen("expandable_list")
    data object ImagePicker : Screen("image_picker")
    data object BottomSheet : Screen("bottom_sheet")
    data object DateAndTimePicker : Screen("date_time")
    data object OnBoarding : Screen("on_boarding")
    data object Parallax : Screen("parallax")
    data object ExpandableText : Screen("expandable_text")
    data object HomeBottomNavScreen : Screen("home_bottom_nav")
    data object PopularBottomNavScreen : Screen("popular_bottom_nav")
    data object TopRatedBottomNavScreen : Screen("top_rated_bottom_nav")
    data object UpComingBottomNavScreen : Screen("up_coming_bottom_nav")

    data object HomeNav : Screen("home_bottom_nav", title = R.string.home, navIcon = {
        Icon(
            Icons.Filled.Home,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })

    data object PopularNav : Screen("popular_bottom_nav", title = R.string.popular, navIcon = {
        Icon(
            Icons.Filled.Timeline,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })

    data object TopRatedNav : Screen("top_rated_bottom_nav", title = R.string.top_rate, navIcon = {
        Icon(
            Icons.Filled.Star,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })

    data object UpcomingNav : Screen("up_coming_bottom_nav", title = R.string.up_coming, navIcon = {
        Icon(
            Icons.Filled.KeyboardArrowDown,
            contentDescription = "search",
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })
}
@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        Screen.Home.route -> stringResource(id = R.string.home)
        else -> {
            ""
        }
    }
}
@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}
