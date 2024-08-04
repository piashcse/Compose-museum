package com.piashcse.compose_museum.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ExpandableList(navController: NavController) {
    val items = listOf(
        ExpandableItem("Item 1", "Detail 1"),
        ExpandableItem("Item 2", "Detail 2"),
        ExpandableItem("Item 3", "Detail 3")
    )
    ExpandableList(items)
}

data class ExpandableItem(val title: String, val detail: String)

@Composable
fun ExpandableList(items: List<ExpandableItem>) {
    val expandedState = remember { mutableStateMapOf<Int, Boolean>() }
    LazyColumn {
        itemsIndexed(items) { index, item ->
            ExpandableListItem(item = item,
                isExpanded = expandedState[index] == true,
                onClick = { expandedState[index] = !(expandedState[index] ?: false) })
        }
    }
}

@Composable
fun ExpandableListItem(item: ExpandableItem, isExpanded: Boolean, onClick: () -> Unit) {
    val rotation by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f, label = "")

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .clickable { onClick() }
        .padding(16.dp)
        .animateContentSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f)
            )
            Icon(imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                modifier = Modifier.graphicsLayer {
                    rotationZ = rotation
                })
        }
        if (isExpanded) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.detail, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
