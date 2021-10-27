package com.bhardwaj.meditationapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bhardwaj.meditationapp.R

val gothic = FontFamily(
    listOf(
        Font(R.font.regular, FontWeight.Normal),
        Font(R.font.medium, FontWeight.Medium),
        Font(R.font.semibold, FontWeight.SemiBold),
        Font(R.font.bold, FontWeight.Bold),
        Font(R.font.black, FontWeight.Black),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        color = AquaBlue,
        fontFamily = gothic,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    h1 = TextStyle(
        color = TextWhite,
        fontFamily = gothic,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    h2 = TextStyle(
        color = TextWhite,
        fontFamily = gothic,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
)