package com.example.thisappwillbefunny.presentation.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.thisappwillbefunny.presentation.ui.elements.text.ButtonText
import com.example.thisappwillbefunny.utils.UiConst

@Composable
fun AppButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    bgColor: Color
) = Button(
    onClick = { onClick() },
    modifier = modifier,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = bgColor
    )
) {
    ButtonText(value = text)
}

@Composable
fun AppButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    bgColor: Brush
) = Button(
    onClick = { onClick() },
    modifier = modifier,
    colors = ButtonDefaults.buttonColors(
        backgroundColor = Color.Transparent
    )
) {
    Box(modifier = Modifier.background(bgColor), contentAlignment = Alignment.Center) {
        ButtonText(value = text)
    }
}


@Composable
fun AppButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    bgColor: Brush,
    icon: Int,
    contentColor: Color
) = Button(
    onClick = { onClick() },
    modifier = Modifier.height(UiConst.Size.BUTTON_HEIGHT).then(modifier),
    colors = ButtonDefaults.buttonColors(
        backgroundColor = Color.Transparent
    )
) {
    Row(
        modifier = Modifier.background(bgColor).fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(UiConst.Size.BtnIconSize)
        )
        ButtonText(value = text, color = contentColor)
    }
}

@Composable
fun AppButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    bgColor: Color,
    icon: Int,
    contentColor: Color
) = Button(
    onClick = { onClick() },
    modifier = Modifier.height(UiConst.Size.BUTTON_HEIGHT).fillMaxWidth().then(modifier),
    colors = ButtonDefaults.buttonColors(
        backgroundColor = bgColor
    )
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(UiConst.Size.BtnIconSize)
        )
        ButtonText(value = text, color = contentColor)
    }
}