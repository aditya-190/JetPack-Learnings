package com.bhardwaj.meditationapp.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bhardwaj.meditationapp.Feature
import com.bhardwaj.meditationapp.R
import com.bhardwaj.meditationapp.ui.theme.*
import kotlin.math.abs

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            HeadingSection()
            SubHeadingChips()
            CurrentMeditation()
        }
    }
}

@Composable
fun HeadingSection(name: String = "Aditya") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good Morning, $name",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
            Text(
                text = "We wish you have a good day?",
                style = MaterialTheme.typography.body2,
                color = TextWhite
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search Icon",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun SubHeadingChips(
    chips: List<String> = listOf("Sweet", "Sleep", "Depression", "Insomia")
) {
    var selectedChipIndex by remember { mutableStateOf(0) }
    LazyRow {
        items(chips.size) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (selectedChipIndex == it) ButtonBlue else DarkerButtonBlue)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = chips[it],
                    color = TextWhite
                )
            }
        }
    }
}

@Composable
fun CurrentMeditation() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(LightRed)
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Daily Thought",
                    style = MaterialTheme.typography.body1,
                    color = TextWhite
                )
                Text(
                    text = "Meditation • 3-10 min",
                    style = MaterialTheme.typography.body2,
                    color = TextWhite
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play Icon",
                tint = Color.White,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(ButtonBlue)
                    .padding(10.dp)
                    .size(16.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(
    features: List<Feature>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(16.dp)
        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, bottom = 100.dp, end = 7.5.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) { i ->
                BoxWithConstraints(
                    modifier = Modifier
                        .padding(7.5.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(features[i].darkColor)
                ) {
                    val width = constraints.maxWidth
                    val height = constraints.maxHeight

                    val mediumColoredPoint1 = Offset(0F, height * 0.3F)
                    val mediumColoredPoint2 = Offset(width * 0.1F, height * 0.35F)
                    val mediumColoredPoint3 = Offset(width * 0.4F, height * 0.05F)
                    val mediumColoredPoint4 = Offset(width * 0.75F, height * 0.7F)
                    val mediumColoredPoint5 = Offset(width * 1.4F, -height.toFloat())

                    val mediumColoredPath = Path().apply {
                        moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
                        standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
                        standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
                        standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
                        standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
                        lineTo(width.toFloat() + 100F, height.toFloat() + 100F)
                        lineTo(-100F, height.toFloat() + 100F)
                        close()
                    }

                    val lightColoredPoint1 = Offset(0F, height * 0.35F)
                    val lightColoredPoint2 = Offset(width * 0.1F, height * 0.4F)
                    val lightColoredPoint3 = Offset(width * 0.3F, height * 0.35F)
                    val lightColoredPoint4 = Offset(width * 0.65F, height.toFloat())
                    val lightColoredPoint5 = Offset(width * 1.4F, -height.toFloat() / 3F)

                    val lightColoredPath = Path().apply {
                        moveTo(lightColoredPoint1.x, lightColoredPoint1.y)
                        standardQuadFromTo(lightColoredPoint1, lightColoredPoint2)
                        standardQuadFromTo(lightColoredPoint2, lightColoredPoint3)
                        standardQuadFromTo(lightColoredPoint3, lightColoredPoint4)
                        standardQuadFromTo(lightColoredPoint4, lightColoredPoint5)
                        lineTo(width.toFloat() + 100F, height.toFloat() + 100F)
                        lineTo(-100F, height.toFloat() + 100F)
                        close()
                    }

                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawPath(
                            path = mediumColoredPath,
                            color = features[i].mediumColor
                        )

                        drawPath(
                            path = lightColoredPath,
                            color = features[i].lightColor
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = features[i].title,
                            style = MaterialTheme.typography.h2,
                            lineHeight = 26.sp,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                        )

                        Icon(
                            painter = painterResource(id = features[i].iconId),
                            contentDescription = features[i].title,
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.BottomStart)
                        )

                        Text(
                            text = "Start",
                            color = TextWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable{

                            }
                                .align(Alignment.BottomEnd)
                                .
                        )
                    }
                }
            }
        }
    }
}

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2F,
        abs(from.y + to.y) / 2F
    )
}







