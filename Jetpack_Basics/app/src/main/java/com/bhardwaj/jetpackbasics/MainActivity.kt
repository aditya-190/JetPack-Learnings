package com.bhardwaj.jetpackbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bhardwaj.jetpackbasics.ui.theme.JetpackBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakingMaterialCardView(
                painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Simply an Image.",
                title = "Aditya Bhardwaj Card View"
            )
        }
    }
}

@Composable
fun UseOfColumn() {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Hello")
        Text(text = "World")
        Text(text = "Hello")
    }
}

@Composable
fun UseOfRow() {
    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Hello")
        Text(text = "World")
        Text(text = "Hello")
    }
}

@Composable
fun UseOfInteractions() {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .border(1.dp, Color.Black)
            .padding(17.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Hello",
            modifier = Modifier
                .clickable {

                }
        )
        Text(text = "World")
    }
}

@Composable
fun MakingMaterialCardView(
    painter: Painter,
    contentDescription: String,
    title: String,
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(0.7f)
            .fillMaxHeight(0.5f),
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp
    ) {
        Box {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewMaterialCard() {
    MakingMaterialCardView(
        painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Simply an Image.",
        title = "Aditya Bhardwaj Card View"
    )
}