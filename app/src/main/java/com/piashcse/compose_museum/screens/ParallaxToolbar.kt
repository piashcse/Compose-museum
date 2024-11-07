package com.piashcse.compose_museum.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piashcse.compose_museum.R
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun ParallaxEffect(navController: NavController) {
    val state = rememberCollapsingToolbarScaffoldState()

    var enabled by remember { mutableStateOf(true) }

    Box {
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbarModifier = Modifier.background(MaterialTheme.colorScheme.primary),
            enabled = enabled,
            toolbar = {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_illustration_shopping),
                    modifier = Modifier
                        .parallax(0.5f)
                        .height(300.dp)
                        .graphicsLayer {
                            // change alpha of Image as the toolbar expands
                            alpha = state.toolbarState.progress
                        },
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    List(100) { "Hello World!! $it" }
                ) {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = enabled, onCheckedChange = { enabled = !enabled })

            Text("Enable collapse/expand", fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParallaxEffectPreview() {
    val navController = rememberNavController()
    ParallaxEffect(navController)
}