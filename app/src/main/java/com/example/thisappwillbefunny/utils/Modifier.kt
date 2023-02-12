package com.example.thisappwillbefunny.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.example.thisappwillbefunny.presentation.navigation.CHOOSE_ACTIVITY_ROUTE

fun Modifier.designTip() = this.fillMaxSize().background(Color.Black.copy(.8f))
fun Modifier.makeHorizontalLine() = this.fillMaxWidth().height(UiConst.Size.LINE_HEIGHT).background(Color.Gray)
fun Modifier.swipeRightToReturn(action: () -> Unit) = this.pointerInput(Unit) {
    detectDragGestures { _, dragAmount ->
        val (x, y) = dragAmount

        when {
            x > 140 -> action( )
        }
    }
}

fun Modifier.createVerticalLine() = this.fillMaxHeight().width(UiConst.Size.LINE_HEIGHT).background(Color.Red)

fun Modifier.createButton(action: () -> Unit) = this
    .clip(UiConst.Round.SMALL)
    .clickable { action() }
    .clip(UiConst.Round.SMALL)
    .height(UiConst.Size.EXIT_BUTTON)
    .width(UiConst.Size.widthButton)