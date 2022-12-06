package com.piashcse.compose_museum.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.piashcse.compose_museum.components.slider.ViewPagerSlider

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(navController: NavController) {
    Column {
        ViewPagerSlider()
    }
}