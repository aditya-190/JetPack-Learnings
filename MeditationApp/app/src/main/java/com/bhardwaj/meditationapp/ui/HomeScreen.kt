package com.bhardwaj.meditationapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bhardwaj.meditationapp.R
import com.bhardwaj.meditationapp.ui.theme.*

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
            FeatureSection()
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
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "We wish you have a good day?",
                style = MaterialTheme.typography.body2
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
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = "Meditation 3-10 min",
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

@Composable
fun FeatureSection() {
}