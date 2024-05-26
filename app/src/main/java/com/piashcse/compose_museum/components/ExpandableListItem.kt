package com.piashcse.compose_museum.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableListItem() {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 10.dp, end = 15.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = { expanded = !expanded })
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(end = 32.dp)
                    .fillMaxWidth()
            ) {
                TitleAndSubtitle(
                    title = "Expandable item title",
                    subtitle = "There are more items below! 👇"
                )
                Icon(
                    Icons.Default.run {
                        if (expanded)
                            ArrowDropUp
                        else
                            ArrowDropDown
                    },
                    contentDescription = "ArrowIcon",
                    tint = Color.LightGray,
                )
            }
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(
                    animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
                ),
                exit = shrinkVertically(
                    animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
                ),
            ) {
                Divider(modifier = Modifier.height(1.dp))
                Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 15.dp)) {
                    Spacer(modifier = Modifier.height(10.dp))
                    ExtraItem(
                        item = Item(
                            "Look at the date here",
                            "March 2, 1998"
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(modifier = Modifier.height(1.dp))
                    Spacer(modifier = Modifier.height(10.dp))

                    ExtraItem(
                        item = Item(
                            "Message about stuff",
                            "June 21, 1982"
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(modifier = Modifier.height(1.dp))
                    Spacer(modifier = Modifier.height(10.dp))

                    ExtraItem(
                        item = Item(
                            "Look at the date here",
                            "March 2, 1998"
                        )
                    )

                }
            }
        }
    }
}

@Composable
fun TitleAndSubtitle(
    title: String,
    subtitle: String
) {
    Column(verticalArrangement = Arrangement.Center) {
        Text(text = title)
        Text(text = subtitle)
    }
}

@Composable
fun ExtraItem(item: Item) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = item.title)
        Text(text = item.date)
    }
}

data class Item(
    val title: String,
    val date: String
)


