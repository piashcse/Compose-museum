package com.piashcse.compose_museum.navigation

import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
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
    object Home : Screen("home")
    object ImageSlider : Screen("image_slider")
    object TabView : Screen("tab_view")
    object ExpandableList : Screen("expandable_list")
    object ImagePicker : Screen("image_picker")
    object BottomSheet : Screen("bottom_sheet")
    object DateAndTimePicker : Screen("date_time")
    object OnBoarding : Screen("on_boarding")
}