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
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunWithTextFieldsButtonsAndSnackBar()
        }
    }
}

@Suppress("unused")
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

@Suppress("unused")
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

@Suppress("unused")
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

@Suppress("unused")
@Composable
fun MakingMaterialCardView() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.4f),
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Name - Aditya Bhardwaj",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
            )

            Text(
                text = "Aditya Bhardwaj",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Suppress("unused")
@Composable
fun FunWithTextStyles(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp
                    )
                ) {
                    append(
                        "A"
                    )
                }
                append("ditya ")

                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp
                    )
                ) {
                    append(
                        "B"
                    )
                }
                append("hardwaj")
            },
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.LineThrough,
        )
    }
}

@Suppress("unused")
@Composable
fun JetPackComposeInternalState() {
    val color = remember { mutableStateOf(Color.Yellow) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color.value)
            .clickable {
                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            }
    )
}

@Suppress("unused")
@Composable
fun JetPackComposeExternalState() {
    val color = remember { mutableStateOf(Color.Yellow) }

    Column(modifier = Modifier.fillMaxSize()) {
        JetPackComposeExternalStateHelperFunction1 { newColor ->
            color.value = newColor
        }
        JetPackComposeExternalStateHelperFunction2(color)
    }
}

@Composable
fun ColumnScope.JetPackComposeExternalStateHelperFunction1(
    updateColorUsingLambdaFunction: (Color) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(Color.Red)
            .weight(1f)
            .fillMaxSize()
            .clickable {
                updateColorUsingLambdaFunction(
                    Color(
                        Random.nextInt(),
                        Random.nextInt(),
                        Random.nextInt()
                    )
                )
            }
    )
}

@Composable
fun ColumnScope.JetPackComposeExternalStateHelperFunction2(
    color: MutableState<Color>
) {
    Box(
        modifier = Modifier
            .background(color.value)
            .weight(1f)
            .fillMaxSize()
    )
}

@Suppress("unused")
@Composable
fun FunWithTextFieldsButtonsAndSnackBar() {
    val scaffoldState = rememberScaffoldState()
    var textFieldState by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            TextField(
                value = textFieldState,
                label = {
                    Text(text = "Enter Your Name")
                },
                onValueChange = { newString ->
                    textFieldState = newString
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(textFieldState)
                    }
                },
                modifier = Modifier.align(Alignment.End)

            ) {
                Text(text = "Let's See What you typed.")
            }
        }
    }
}