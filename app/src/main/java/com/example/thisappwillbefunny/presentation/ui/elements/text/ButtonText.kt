package com.example.thisappwillbefunny.presentation.ui.elements.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.thisappwillbefunny.presentation.ui.font.Sassy

@Composable
fun ButtonText(
    value: String,
    color: Color = Color.White
) =
    Text(
        text = value,
        fontFamily = Sassy,
        style = MaterialTheme.typography.button,
        color = color
    )