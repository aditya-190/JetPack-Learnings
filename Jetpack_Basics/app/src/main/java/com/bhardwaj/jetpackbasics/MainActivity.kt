package com.bhardwaj.jetpackbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HowToMakeAnimatedCircularProgressBar(percentage = 0.7f, number = 100)
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
fun InternalState() {
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
fun ExternalState() {
    val color = remember { mutableStateOf(Color.Yellow) }

    Column(modifier = Modifier.fillMaxSize()) {
        ExternalStateHelperFunction1 { newColor ->
            color.value = newColor
        }
        ExternalStateHelperFunction2(color)
    }
}

@Composable
fun ColumnScope.ExternalStateHelperFunction1(
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
fun ColumnScope.ExternalStateHelperFunction2(
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
fun TextFieldsButtonsAndSnackBar() {
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

@Suppress("unused")
@Composable
fun ScrollableColumns() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        for (i in 1..25) {
            Text(
                text = "Item $i",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            )
        }
    }
}

@Suppress("unused")
@Composable
fun UseOfLazyColumnWithItems() {
    LazyColumn {
        items(count = 5000) {
            Text(
                text = "Item $it",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            )
        }
    }
}

@Suppress("unused")
@Composable
fun UseOfLazyColumnWithItemsIndexed() {
    LazyColumn {
        itemsIndexed(
            listOf(
                "Hey",
                "There",
                "I",
                "AM",
                "ADITYA",
                "BHARDWAJ",
                "LEARNING",
                "BASICS",
                "OF",
                "JETPACK",
                "COMPOSE"
            )
        ) { index: Int, item: String ->
            Text(
                text = "$item $index",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            )
        }
    }
}

@Suppress("unused")
@Composable
fun UseOfConstraintLayout() {
    // You need to IMPORT Constraint Layout First. Check build.gradle - Module File :)

    val constraints = ConstraintSet {
        val blackBox1 = createRefFor("blackBox1")
        val blackBox2 = createRefFor("blackBox2")

        val guideline = createGuidelineFromTop(0.5f)

        constrain(blackBox1) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            width = Dimension.percent(0.2f)
            height = Dimension.percent(0.2f)
        }

        constrain(blackBox2) {
            top.linkTo(blackBox1.top)
            end.linkTo(parent.end)
            width = Dimension.percent(0.2f)
            height = Dimension.percent(0.2f)
        }

        createHorizontalChain(blackBox1, blackBox2, chainStyle = ChainStyle.Spread)
    }

    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .layoutId("blackBox1")
        )

        Box(
            modifier = Modifier
                .background(Color.Black)
                .layoutId("blackBox2")
        )
    }
}

@Suppress("unused")
@Composable
fun UseOfSideEffectsAndEffectsHandler() {
    // TODO: Learn More About This and then Update Best Here.
}

@Suppress("unused")
@Composable
fun UseOfSimpleAnimation() {
    var sizeState by remember { mutableStateOf(200.dp) }
    val size1 by animateDpAsState(
        targetValue = sizeState,
        tween(
            durationMillis = 500,
            delayMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

    val size2 by animateDpAsState(
        targetValue = sizeState,
        spring(
            Spring.DampingRatioHighBouncy
        )
    )

    val size3 by animateDpAsState(
        targetValue = sizeState,
        keyframes {
            durationMillis = 1000
            sizeState at 0 with LinearOutSlowInEasing
            sizeState * 1.5f at 1000 with FastOutSlowInEasing
            sizeState * 2f at 5000 with FastOutLinearInEasing
        }
    )

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Cyan,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(size1)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeState += 50.dp
        }) {
            Text("Increase Size")
        }
    }
}

@Suppress("unused")
@Composable
fun HowToMakeAnimatedCircularProgressBar(
    percentage: Float,
    number: Int,
    radius: Dp = 50.dp,
    color: Color = Color.Blue,
    strokeWidth: Dp = 8.dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
            .padding(16.dp)
    ) {
        Canvas(
            modifier = Modifier.size(radius * 2f)
        ) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = currentPercentage.value * 360,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (currentPercentage.value * number).toInt().toString(),
            color = Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    }
}















