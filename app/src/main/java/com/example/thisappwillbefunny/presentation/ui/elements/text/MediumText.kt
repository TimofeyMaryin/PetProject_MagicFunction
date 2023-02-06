package com.example.thisappwillbefunny.presentation.ui.elements.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.thisappwillbefunny.presentation.ui.font.Sassy

@Composable
fun MediumText(
    value: String,
    color: Color,
    modifier: Modifier = Modifier,
) =
    Text(
        text = value,
        style = MaterialTheme.typography.subtitle1,
        fontFamily = Sassy,
        color = color,
        modifier = modifier
    )