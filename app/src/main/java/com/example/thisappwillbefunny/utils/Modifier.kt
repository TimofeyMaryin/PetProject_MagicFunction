package com.example.thisappwillbefunny.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.example.thisappwillbefunny.presentation.navigation.CHOOSE_ACTIVITY_ROUTE

fun Modifier.designTip() = this.fillMaxSize().background(Color.Black.copy(.8f))
fun Modifier.makeHorizontalLine() = this.fillMaxWidth().height(UiConst.Size.LINE_HEIGHT).background(Color.Gray)
fun Modifier.swipeRightToReturn(action: () -> Unit) = this.pointerInput(Unit) {
    detectDragGestures { _, dragAmount ->
        val (x, y) = dragAmount

        when {
            x > 140 -> action()
        }
    }
}