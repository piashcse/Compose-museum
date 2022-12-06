package com.piashcse.compose_museum.components.slider

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.lerp
import com.google.accompanist.pager.*
import com.piashcse.compose_museum.ui.theme.Purple200
import com.piashcse.compose_museum.ui.theme.Purple500
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue


@ExperimentalPagerApi
@Composable
fun ViewPagerSlider(){
    val pagerState  = rememberPagerState()

    LaunchedEffect(Unit){
        while (true){
            yield()
            delay(3000)
            tween<Float>(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column(modifier = Modifier.height(180.dp)) {
        Column(modifier = Modifier
            .height(0.dp)
            .fillMaxWidth()
            .background(color = Purple500),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "View Pager Slide",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(10.dp))
        HorizontalPager(
            count = kidsList.size,
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
        ) { page ->
            Card(modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    lerp(
                        start = 0.85.dp,
                        stop = 1.dp,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale.value
                        scaleY = scale.value

                    }
                   1

                }
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 0.dp),
                shape = RoundedCornerShape(8.dp)
            ) {

                val newKids = kidsList[page]
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(
                        id = newKids.imgUri
                    ),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Column(modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(15.dp)
                    ) {

                        Text(
                            text = newKids.title,
                            style = MaterialTheme.typography.h5,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                     /*   val ratingBar = RatingBar(
                            LocalContext.current, null, 10
                        ).apply {
                            rating = newKids.rating
                            progressDrawable.setColorFilter(
                                android.graphics.Color.parseColor("#FF0000"),
                                PorterDuff.Mode.SRC_ATOP
                            )
                        }

                        AndroidView(factory ={ratingBar},
                            modifier = Modifier.padding(0.dp,8.dp,0.dp,0.dp)
                        )*/
                        Text(
                            text = newKids.desc,
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(0.dp,8.dp,0.dp,0.dp)
                        )


                    }

                }


            }

        }
        //Horizontal dot indicator
        HorizontalPagerIndicator(
            activeColor = Purple500 ,
            inactiveColor = Purple200,
            indicatorWidth = 6.dp,
            pagerState = pagerState,modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(12.dp)
        )

    }

}