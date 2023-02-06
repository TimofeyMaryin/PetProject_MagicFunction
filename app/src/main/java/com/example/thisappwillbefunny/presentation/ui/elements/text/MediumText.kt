package com.example.thisappwillbefunny.presentation.ui.elements.text

import android.graphics.fonts.FontFamily
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.thisappwillbefunny.presentation.ui.font.Raleway
import com.example.thisappwillbefunny.presentation.ui.font.Sassy

@Composable
fun MediumText(
    value: String,
    color: Color = MaterialTheme.colors.background,
    modifier: Modifier = Modifier,
    fontFamily: androidx.compose.ui.text.font.FontFamily = Raleway,
    fontWeight: FontWeight = FontWeight.Normal
) =
    Text(
        text = value,
        style = MaterialTheme.typography.subtitle1,
        fontFamily = fontFamily ,
        color = color,
        modifier = modifier,
        fontWeight = fontWeight
    )