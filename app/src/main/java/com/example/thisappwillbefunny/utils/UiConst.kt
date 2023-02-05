package com.example.thisappwillbefunny.utils


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object UiConst {
    object Round {
        val BIG_BLOCK_ROUND = RoundedCornerShape(40.dp)
        val SMALL = RoundedCornerShape(8.dp)
    }

    object Padding {
        val CONTAINER = 23.dp
        val BETWEEN_ELEMENT = 30.dp
        val MEDIUM = 15.dp
    }

    object Size {
        val EXIT_BUTTON = 40.dp
        val HEIGHT_FEATURE_EL = 60.dp
        val BtnIconSize = 20.dp
        val BUTTON_HEIGHT = 60.dp
    }

    object Color {
        val EXIT_BUTTON = androidx.compose.ui.graphics.Color.Yellow
        val EXIT_BUTTON_ICON = androidx.compose.ui.graphics.Color.Red
    }

}

object ContentConst {

    object ListOfFeature {

        object First {
            const val title = "Get Random Cats"
            val bg = Brush.horizontalGradient(ElConst.firstBgBrush)
            val textColor = Color.White
        }

        object Second {
            const val title = "Select Cat`s description internet status"
            val bg = Brush.horizontalGradient(colorStops = ElConst.secondBgBrush)
            val textColor = Color.White
        }

        object Three {
            const val title = "What`s you will doing"
            val bg = Brush.horizontalGradient(colorStops = ElConst.threeBgBrush)
            val textColor = Color.White
        }

    }

    private object ElConst {
        private const val defaultAlpha = .4f
        val firstBgBrush = listOf(Color.Red.copy(defaultAlpha), Color.Blue.copy(defaultAlpha))
        val secondBgBrush = arrayOf(
            0.0f to Color.Yellow.copy(defaultAlpha),
            0.2f to Color.Red.copy(defaultAlpha),
            1f to Color.Blue.copy(defaultAlpha)
        )

        val threeBgBrush = arrayOf(
            0.25f to Color(0xFF03001e).copy(defaultAlpha),
            0.50f to Color(0xFF7303c0).copy(defaultAlpha),
            0.75f to Color(0xFFec38bc).copy(defaultAlpha),
            1.00f to Color(0xFF370729).copy(defaultAlpha)
        )
    }

}