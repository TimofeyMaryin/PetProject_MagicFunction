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
        val CONTAINER = 30.dp
        val BETWEEN_ELEMENT = 30.dp
        val MEDIUM = 15.dp
    }

    object Size {
        val EXIT_BUTTON = 100.dp
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
        val firstBgBrush = listOf(Color.Red, Color.Blue)
        val secondBgBrush = arrayOf(
            0.0f to Color.Yellow,
            0.2f to Color.Red,
            1f to Color.Blue
        )

        val threeBgBrush = arrayOf(
            0.25f to Color(0xFF03001e),
            0.50f to Color(0xFF7303c0),
            0.75f to Color(0xFFec38bc),
            1.00f to Color(0xFF370729)
        )
    }

}