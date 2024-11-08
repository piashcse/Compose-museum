package com.piashcse.compose_museum.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ImageSlider(navController: NavController) {
    val images = listOf(
        "https://image.tmdb.org/t/p/w500//j736cRzBtEPCm0nHnpRN1prqiqj.jpg" to "First Image",
        "https://image.tmdb.org/t/p/w500/3EpZ2ksjijmdr8BhISP03PYzNFW.jpg" to "Second Image",
        "https://image.tmdb.org/t/p/w500/flSncTaSISRqrqoJ18ZBmThR4Ee.jpg" to "Third Image"
    )
    ImageSlider(images = images, modifier = Modifier.fillMaxSize())
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(images: List<Pair<String, String>>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState {
        3
    }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000) // Delay for auto-scrolling
            coroutineScope.launch {
                pagerState.animateScrollToPage(
                    (pagerState.currentPage + 1) % images.size
                )
            }
        }
    }

    Column(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.height(250.dp)
        ) { page ->
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = rememberAsyncImagePainter(images[page].first),
                    contentDescription = images[page].second,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = images[page].second,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(8.dp)
                        .background(Color.Black.copy(alpha = 0.2f), shape = RoundedCornerShape(18))
                        .padding(start = 6.dp, end = 6.dp)
                )
            }
        }
        IndicatorRow(size = images.size, index = pagerState.currentPage)
    }
}

@Composable
fun IndicatorRow(size: Int, index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(size) { i ->
            val color by animateColorAsState(
                targetValue = if (i == index) Color.Blue else Color.Gray,
                animationSpec = tween(durationMillis = 300), label = ""
            )
            Box(
                modifier = Modifier
                    .padding(1.dp)
                    .width(14.dp)
                    .height(5.dp)
                    .background(color, shape = RoundedCornerShape(50))

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageSliderPreview() {
    val navController = rememberNavController()
    ImageSlider(navController)
}