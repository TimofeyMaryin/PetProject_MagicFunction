package com.example.thisappwillbefunny.presentation.ui.elements.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.thisappwillbefunny.presentation.ui.font.Sassy

@Composable
fun LargeText(
    value: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    textAlign: TextAlign = TextAlign.Start
) =
    Text(
        text = value,
        modifier = modifier,
        style = MaterialTheme.typography.h6,
        fontFamily = Sassy,
        color = color,
        textAlign = textAlign
    )