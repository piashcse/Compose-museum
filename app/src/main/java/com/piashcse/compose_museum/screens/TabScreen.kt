package com.piashcse.compose_museum.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(navController: NavController) {
    val pagerState = rememberPagerState{
        3
    }
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("Tab 1", "Tab 2", "Tab 3")

    Column {
        // TabRow
        TabRow(
            selectedTabIndex = pagerState.currentPage,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(title) }
                )
            }
        }
        // HorizontalPager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> FirstTabContent()
                1 -> SecondTabContent()
                2 -> ThirdTabContent()
            }
        }
    }
}

@Composable
fun FirstTabContent() {
    Text(text = "First Tab Content")
}

@Composable
fun SecondTabContent() {
    Text(text = "Second Tab Content")
}

@Composable
fun ThirdTabContent() {
    Text(text = "Third Tab Content")
}

@Preview(showBackground = true)
@Composable
fun TabScreenPreview() {
    val navController = rememberNavController()
    TabScreen(navController)
}