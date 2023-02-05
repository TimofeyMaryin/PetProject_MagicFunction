package com.example.thisappwillbefunny.presentation.ui.elements.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thisappwillbefunny.presentation.ui.font.Sassy

@Composable
fun TitleText(
    value: String,
    modifier: Modifier,
) =
    Text(
        text = value,
        fontFamily = Sassy,
        style = MaterialTheme.typography.h4,
        modifier = modifier
    )