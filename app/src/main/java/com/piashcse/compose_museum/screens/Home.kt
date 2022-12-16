package com.piashcse.compose_museum.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.piashcse.compose_museum.navigation.Screen

@Composable
fun Home(navController: NavHostController){
    Column {
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.ImageSlider.route)
        }) {
            Text("Image Slider")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.TabView.route)
        }) {
            Text("Tav View")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.ExpandableList.route)
        }) {
            Text("Expandable List")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.ImagePicker.route)
        }) {
            Text("Image Picker")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.BottomSheet.route)
        }) {
            Text("Bottom Sheet")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.DateAndTimePicker.route)
        }) {
            Text("Date and Time Picker")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.OnBoarding.route)
        }) {
            Text("OnBoarding")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.Parallax.route)
        }) {
            Text("Parallax Toolbar")
        }
    }
}