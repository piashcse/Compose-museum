package com.piashcse.compose_museum.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.piashcse.compose_museum.navigation.Navigation
import com.piashcse.compose_museum.navigation.Screen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState) {
        Navigation(
            navController = navController,
            modifier = Modifier.padding(it),
            Screen.Home.route
        )
    }
}