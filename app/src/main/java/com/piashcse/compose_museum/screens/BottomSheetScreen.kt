package com.piashcse.compose_museum.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(navController: NavController) {
    val bottomSheetItems = listOf(
        BottomSheetItem(title = "Notification", icon = Icons.Default.Notifications),
        BottomSheetItem(title = "Mail", icon = Icons.Default.MailOutline),
        BottomSheetItem(title = "Scan", icon = Icons.Default.Search),
        BottomSheetItem(title = "Edit", icon = Icons.Default.Edit),
        BottomSheetItem(title = "Favorite", icon = Icons.Default.Favorite),
        BottomSheetItem(title = "Settings", icon = Icons.Default.Settings)
    )
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                content = {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = "Bottom Sheet",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                        color = Color.White
                    )
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3)
                    ) {
                        items(bottomSheetItems.size, itemContent = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp)
                                    .clickable {
                                        scope.launch { scaffoldState.bottomSheetState.partialExpand() }
                                    },
                            ) {
                                Spacer(modifier = Modifier.padding(8.dp))
                               Icon(
                                    bottomSheetItems[it].icon,
                                    bottomSheetItems[it].title,
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                                Text(
                                    text = bottomSheetItems[it].title,
                                    color = Color.White
                                )
                            }

                        })
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    //.background(Color(0xFF6650a4))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF8E2DE2),
                                Color(0xFF4A00E0)
                            )
                        ),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 10.dp)
                    )
                    .padding(16.dp),

                )
        },
        sheetPeekHeight = 0.dp
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                scope.launch { scaffoldState.bottomSheetState.expand() }
            }) {
                Text("Show Bottom Sheet")
            }
        }
    }
}

data class BottomSheetItem(val title: String, val icon: ImageVector)

@Preview(showBackground = true)
@Composable
fun BottomSheetScreenPreview() {
    val navController = rememberNavController()
    BottomSheetScreen(navController)
}