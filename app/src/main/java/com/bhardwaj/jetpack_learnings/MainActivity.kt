package com.bhardwaj.jetpack_learnings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier
import java.nio.file.Files.size

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("Aditya", "Learning Jetpack Compose"))
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Sample Image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Column {
            Text(text = msg.author)
            Text(text = msg.body)
        }
    }
}

data class Message(
    val author: String,
    val body: String
)

@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(msg = Message("Aditi", "Hello"))
}