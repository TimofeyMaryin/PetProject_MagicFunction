package com.example.thisappwillbefunny.presentation.ui.elements

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.presentation.ui.font.Raleway
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.listOfTipsInternetStatus
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselTips() {
    val state = rememberLazyListState()
    val snappingLayout = remember(state){ SnapLayoutInfoProvider(state) }
    val flingBehavior  = rememberSnapFlingBehavior(snappingLayout)


    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        state = state,
        flingBehavior = flingBehavior
    ) {
        items(listOfTipsInternetStatus.size){
            Box(
                modifier = Modifier.fillParentMaxWidth().padding(bottom = UiConst.Padding.BETWEEN_ELEMENT   ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .clip(UiConst.Round.SMALL)
                        .fillParentMaxWidth(.95f)
                        .background(Color.Blue.copy(.3f))
                        .defaultMinSize(minHeight = UiConst.Size.MIN_HEIGHT_CAROUSEL_ITEM),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LargeText(
                        value = "Tips №${listOfTipsInternetStatus[it].tipsNumber}",
                        fontFamily = Raleway,
                        color = MaterialTheme.colors.background,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    MediumText(
                        value = listOfTipsInternetStatus[it].tips,
                        color = MaterialTheme.colors.background,
                        modifier = Modifier
                            .weight(2f)
                            .padding(start = UiConst.Padding.BETWEEN_ELEMENT)
                    )
                }

            }




        }
    }

}