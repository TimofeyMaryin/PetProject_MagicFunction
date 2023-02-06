package com.example.thisappwillbefunny.presentation.ui.elements.text


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import com.example.thisappwillbefunny.presentation.ui.font.Raleway
import com.example.thisappwillbefunny.utils.UiConst

@Composable
fun SmallText(
    value: String,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = Raleway,
    color: Color = Color.Gray
) =
    Text(
        text = value,
        modifier = modifier,
        fontFamily = fontFamily,
        fontSize = UiConst.Size.SMALL_TEXT_SIZE,
        color = color
    )