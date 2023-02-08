package com.example.thisappwillbefunny.presentation.ui.elements

import android.content.res.Resources.getSystem
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun LoadingShimmerEffect(
    modifier: Modifier
) {
    val gradient = listOf(
        themedDarkBlueSilverLight().copy(alpha = 0.9f),
        themedDarkBlueSilverLight().copy(alpha = 0.3f),
        themedDarkBlueSilverLight().copy(alpha = 0.9f),
        themedDarkBlueSilverLight().copy(alpha = 0.9f),
        themedDarkBlueSilverLight().copy(alpha = 0.9f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(durationMillis = 3200, easing = LinearEasing))
    )

    var positionInRootTopBar by remember { mutableStateOf(Offset.Zero) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.px
    val screenWidth = configuration.screenWidthDp.px

    val diff = screenWidth * translateAnim * 4

    val initialX = diff - (positionInRootTopBar.x) - (screenWidth * 2)
    val endX = (screenWidth * 2) + initialX

    val initialY = -positionInRootTopBar.y + diff
    val endY = screenHeight - positionInRootTopBar.y + diff
    val brush = Brush.linearGradient(
        colors = gradient,
        start = Offset(initialX, initialY),
        end = Offset(endX, endY),
    )
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(brush)
            .height(200.dp)
            .onGloballyPositioned {
                positionInRootTopBar = it.positionInRoot()
            }
            .then(modifier)
    )
}

fun themedDarkBlueSilverLight(): Color = Color(0xFF0B1924)


val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()