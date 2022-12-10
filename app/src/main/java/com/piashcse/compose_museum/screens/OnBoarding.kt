package com.piashcse.compose_museum.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.piashcse.compose_museum.ui.theme.Grey300
import com.piashcse.compose_museum.ui.theme.Grey900
import com.piashcse.compose_museum.ui.theme.Typography
import com.piashcse.compose_museum.R

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoarding(navController: NavController) {
    val pagerState = rememberPagerState()
    Surface(modifier = Modifier.fillMaxSize()) {
        val items = arrayListOf<OnBoardingData>(
            OnBoardingData(
                R.drawable.ic_illustration_shopping,
                "Shop Awesome Products",
                "We have products in different categories including Apparels, Electronics, Accessories, Footwear etc."
            ),

            OnBoardingData(
                R.drawable.ic_illustration_delivery,
                "One Day Delivery",
                "Our delivery team around the clock to provide you the products fast and securely."
            ),

            OnBoardingData(
                R.drawable.ic_illustration_research,
                "Amazing Customer Support",
                "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface."
            )
        )

        OnBoardingPager(
            item = items,
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Grey900)
        )

    }
}

@ExperimentalPagerApi
@Composable
fun OnBoardingPager(
    item: List<OnBoardingData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState, count = item.size) { page ->
                Column(
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = item[page].image),
                        contentDescription = item[page].title,
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = item[page].title,
                        modifier = Modifier.padding(top = 50.dp),
                        color = Color.White,
                        style = Typography.body1
                    )

                    Text(
                        text = item[page].desc,
                        modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
                        color = Color.White,
                        style = Typography.body1,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )

                }
            }

            PagerIndicator(item.size, pagerState.currentPage)
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomSection(pagerState.currentPage)
        }
    }
}

@Composable
fun PagerIndicator(size: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(top = 60.dp)
    ) {
        repeat(size) {
            Indicator(isSelected = it == currentPage)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(1.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) MaterialTheme.colors.primary else Grey300.copy(alpha = 0.5f)
            )
    )
}

@Composable
fun BottomSection(currentPager: Int) {
    Row(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = if (currentPager != 2) Arrangement.SpaceBetween else Arrangement.Center
    ) {

        if (currentPager == 2) {
            OutlinedButton(
                onClick = { },
                shape = RoundedCornerShape(50), // = 40% percent
            ) {
                Text(
                    text = "Get Started",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    color = Grey900
                )
            }
        } else {
            SkipNextButton("Skip", Modifier.padding(start = 20.dp))
            SkipNextButton("Next", Modifier.padding(end = 20.dp))
        }

    }
}

@Composable
fun SkipNextButton(text: String, modifier: Modifier) {
    Text(
        text = text,
        color = Grey300,
        modifier = modifier,
        fontSize = 18.sp,
        style = Typography.body1,
        fontWeight = FontWeight.Medium
    )

}

data class OnBoardingData(
    val image: Int, val title: String, val desc: String
)
