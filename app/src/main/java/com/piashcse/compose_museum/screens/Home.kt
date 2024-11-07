package com.piashcse.compose_museum.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.piashcse.compose_museum.R
import com.piashcse.compose_museum.navigation.Screen

@Composable
fun Home(navController: NavHostController){
    Column(modifier = Modifier.padding(8.dp)) {
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.ImageSlider.route)
        }) {
            Text(stringResource(R.string.image_slider))
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.TabView.route)
        }) {
            Text(stringResource(R.string.tav_view))
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.ExpandableList.route)
        }) {
            Text(stringResource(R.string.expandable_list))
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.ImagePicker.route)
        }) {
            Text(stringResource(R.string.image_picker))
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.BottomSheet.route)
        }) {
            Text(stringResource(R.string.bottom_sheet))
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.HomeBottomNavScreen.route)
        }) {
            Text(stringResource(R.string.bottom_navigation))
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.DateAndTimePicker.route)
        }) {
            Text(stringResource(R.string.date_and_time_picker))
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.OnBoarding.route)
        }) {
            Text(stringResource(R.string.onboarding))
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.Parallax.route)
        }) {
            Text(stringResource(R.string.parallax_toolbar))
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.ExpandableText.route)
        }) {
            Text(stringResource(R.string.expandable_texview))
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.CountryList.route)
        }) {
            Text(stringResource(R.string.country_list))
        }
    }
}