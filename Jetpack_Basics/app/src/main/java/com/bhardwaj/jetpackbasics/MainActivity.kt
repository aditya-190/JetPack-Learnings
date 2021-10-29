package com.bhardwaj.jetpackbasics

import android.os.Bundle
import android.view.MotionEvent
import android.view.animation.OvershootInterpolator
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
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
import androidx.constraintlayout.compose.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HowToMakeTimer()
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
    radius: Dp = 100.dp,
    color: Color = Color.Black,
    strokeWidth: Dp = 10.dp,
    animationDuration: Int = 1500,
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
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(5.dp, Color.Black, RoundedCornerShape(16.dp))
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
            color = color,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,
    barCount: Int = 10
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = remember { constraints.maxWidth / (2F * barCount) }

        Canvas(modifier = modifier) {
            for (i in 0 until barCount) {
                drawRoundRect(
                    color = if (i in 0..activeBars) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2F + barWidth / 2F, 0F),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0F)
                )
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitingAngle: Float = 25F,
    onValueChange: (Float) -> Unit
) {
    var rotation by remember { mutableStateOf(limitingAngle) }
    var touchX by remember { mutableStateOf(0F) }
    var touchY by remember { mutableStateOf(0F) }
    var centerX by remember { mutableStateOf(0F) }
    var centerY by remember { mutableStateOf(0F) }

    Image(
        painter = painterResource(id = R.drawable.knob),
        contentDescription = "Music Knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinate ->
                val windowBounds = coordinate.boundsInWindow()
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            }
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y

                val angle = -atan2(centerX - touchX, centerY - touchY) * (180 / PI).toFloat()

                when (event.action) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitingAngle..limitingAngle) {
                            val fixedAngle = if (angle in -180F..-limitingAngle) {
                                360F + angle
                            } else {
                                angle
                            }
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitingAngle) / (360F - 2 * limitingAngle)
                            onValueChange(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )
}

@ExperimentalComposeUiApi
@Suppress("unused")
@Composable
fun HowToMakeDraggableMusicKnob() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                .padding(30.dp)
        ) {
            var volume by remember { mutableStateOf(0F) }
            val barCount = 20

            MusicKnob(
                modifier = Modifier.size(100.dp)
            ) {
                volume = it
            }

            Spacer(modifier = Modifier.width(20.dp))

            VolumeBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                activeBars = (barCount * volume).roundToInt(),
                barCount = barCount
            )
        }
    }
}

@Suppress("unused")
@Composable
fun HowToMakeAnimatedSplashScreen() {
    // You need to include Navigation Library.

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF202020))
    ) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "splash_screen") {
            composable("splash_screen") {
                val scale = remember {
                    Animatable(0F)
                }

                LaunchedEffect(key1 = true) {
                    scale.animateTo(
                        targetValue = 0.9F,
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = {
                                OvershootInterpolator(2F).getInterpolation(it)
                            }
                        )
                    )
                    delay(3000L)
                    navController.navigate("main_screen")
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.knob),
                        contentDescription = "Logo",
                        modifier = Modifier.scale(scale.value)
                    )
                }
            }

            composable("main_screen") {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Main Screen", color = Color.White)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Suppress("unused")
@Composable
fun UseOfMultiSelectLazyColumn() {
    data class MultiSelectItem(
        val title: String,
        val isSelected: Boolean
    )

    var items by remember {
        mutableStateOf(
            (1..20).map {
                MultiSelectItem(
                    title = "Item $it",
                    isSelected = false
                )
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(items.size) { i ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        items = items.mapIndexed { index, value ->
                            if (i == index) {
                                value.copy(isSelected = !value.isSelected)
                            } else value
                        }
                    }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = items[i].title)
                if (items[i].isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Suppress("unused")
@Composable
fun HowToMakeAnimatedDropDownBox(
    modifier: Modifier = Modifier,
    text: String = "Hello World!",
    isOpened: Boolean = false,
) {
    var isOpen by remember { mutableStateOf(isOpened) }

    val alpha = animateFloatAsState(
        targetValue = if (isOpen) 1F else 0F,
        animationSpec = tween(durationMillis = 300)
    )

    val rotateX = animateFloatAsState(
        targetValue = if (isOpen) 0F else -90F,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = text, color = Color.Black, fontSize = 16.sp)
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open or Close the Drop Down",
                tint = Color.Black,
                modifier = Modifier
                    .clickable {
                        isOpen = !isOpen
                    }
                    .scale(1F, if (isOpen) -1F else 1F)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .graphicsLayer {
                    transformOrigin = TransformOrigin(0.5F, 0F)
                    rotationX = rotateX.value
                }
                .alpha(alpha.value)
                .background(Color(0xFFF2F2F2)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "This is now Revealed.",
                color = Color.Black,
            )
        }
    }
}


@Suppress("unused")
@Composable
fun HowToMakeTimer(
    modifier: Modifier = Modifier,
    totalTime: Long,
    handleColor: Color,
    activeBarColor: Color,
    inActiveBarColor: Color,
    initialValue: Float = 0F,
    strokeWidth: Dp = 5.dp
) {

}




























