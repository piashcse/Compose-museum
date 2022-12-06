package com.piashcse.compose_museum.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.piashcse.compose_museum.components.ExpandableListItem

@Composable
fun ExpandableList(navController: NavController){
    Column {
        ExpandableListItem()
        ExpandableListItem()
        ExpandableListItem()
        ExpandableListItem()
    }
}