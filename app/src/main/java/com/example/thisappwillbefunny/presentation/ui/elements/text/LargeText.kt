package com.example.thisappwillbefunny.presentation.ui.elements.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.thisappwillbefunny.presentation.ui.font.Sassy

@Composable
fun LargeText(
    value: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    textAlign: TextAlign = TextAlign.Start,
    fontFamily: FontFamily? = Sassy,
    fontWeight: FontWeight = FontWeight.Bold,
    fontStyle: FontStyle? = null
) =
    Text(
        text = value,
        modifier = modifier,
        style = MaterialTheme.typography.h6,
        fontFamily = fontFamily,
        color = color,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontStyle = fontStyle,
    )