package com.example.thisappwillbefunny.utils


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object UiConst {
    object Round {
        val BIG_BLOCK_ROUND = RoundedCornerShape(40.dp)
        val NORMAL = RoundedCornerShape(16.dp)
        val SMALL = RoundedCornerShape(8.dp)

        const val SMALL_FLOAT = 8f
    }

    object Padding {
        val CONTAINER = 23.dp
        val BETWEEN_ELEMENT = 30.dp
        val MEDIUM = 15.dp
        val SMALL = 8.dp
        val ZERO = 0.dp
    }

    object Border {
        val bottomSheetBorder = BorderStroke(1.dp, androidx.compose.ui.graphics.Color.Magenta)
    }

    object Size {
        val EXIT_BUTTON = 40.dp
        val HEIGHT_FEATURE_EL = 60.dp
        val WIDTH_TIP_EL = 150.dp
        val BtnIconSize = 20.dp
        val BUTTON_HEIGHT = 60.dp
        val TOP_BAR_HEIGHT = 160.dp
        val MIN_HEIGHT_CAROUSEL_ITEM = 100.dp
        val SMALL_TEXT_SIZE = 13.sp
        val TIPS_VIDEOS = 190.dp

        val ACTIVITY_ITEM_SIZE = 60.dp

        val BOTTOM_SHEET_DETAIL = Pair(50.dp, 4.dp)
        val LINE_HEIGHT = 1.dp
        val BIG_LINE_HEIGHT = 3.dp
        val HEIGHT_BOTTOM_SHEET_EL = 40.dp
    }

    object Color {
        val EXIT_BUTTON = androidx.compose.ui.graphics.Color.Yellow
        val EXIT_BUTTON_ICON = androidx.compose.ui.graphics.Color.Red
    }

    val DEFAULT_BLUR = 14.dp

    object Brushes {
        val BLUE_PINK = Brush.linearGradient(
            0.0f to Color(0xFFFF3CAC),
            0.6f to Color(0xFF784BA0),
            1f to Color(0xFF2B86C5)
        )

        val RAINBOW_BLUE = Brush.radialGradient(
            0.0f to Color(0xFF00f260),
            1f to Color(0xFF0575e6)
        )
        val MIAKA = Brush.radialGradient(
            0.0f to Color(0xFFFC354C),
            1f to Color(0xFF0ABFBC)
        )

        val ActivityItem = Brush.horizontalGradient(
            0.0f to androidx.compose.ui.graphics.Color.White.copy(.5f),
            1.0f to androidx.compose.ui.graphics.Color.White.copy(.7f)
        )


        object ActivityTipsStatus {
            val easy = Pair(
                Brush.horizontalGradient(
                    0.0f to Color(0xFFa8ff78),
                    1.0f to Color(0xFF78ffd6)
                ),
                androidx.compose.ui.graphics.Color.White
            )

            val normal = Pair(
                Brush.horizontalGradient(
                    0.0f to Color(0xFFF7971E),
                    1.0f to Color(0xFFFFD200)
                ),
                androidx.compose.ui.graphics.Color.White
            )


            val hard = Pair(
                Brush.horizontalGradient(
                    0.0f to Color(0xFFED213A),
                    1.0f to Color(0xFF93291E)
                ),
                androidx.compose.ui.graphics.Color.White
            )

        }
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