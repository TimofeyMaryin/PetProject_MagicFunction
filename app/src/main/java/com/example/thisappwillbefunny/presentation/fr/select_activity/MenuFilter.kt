package com.example.thisappwillbefunny.presentation.fr.select_activity

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.FilterElementModel
import com.example.thisappwillbefunny.presentation.ui.elements.Container
import com.example.thisappwillbefunny.utils.UiConst

@Composable
fun MenuFilter(viewModel: SelectActivityViewModel) {


    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){

        LazyRow(
            modifier = Modifier
                .clip(UiConst.Round.NORMAL)
                .fillMaxWidth(.95f)
                .background(Color.White.copy(.8f)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            items(viewModel.listOfFilterElement.size) {
                ContainerElement {
                    MenuFilterItem(value = viewModel.listOfFilterElement[it]) {
                        viewModel.selectFilterEl(it)
                    }
                }
            }
        }
    }

}

@Composable
private fun MenuFilterItem(
    value: FilterElementModel,
    onClick: () -> Unit
) {
    val borderColor by animateColorAsState(
        targetValue = if (value.isActive.value) Color.Red else Color.Transparent,
        spring(stiffness = Spring.StiffnessLow)
    )

    Box(
        modifier = Modifier
            .clip(UiConst.Round.SMALL)
            .size(UiConst.Size.ACTIVITY_ITEM_SIZE)
            .border(
                BorderStroke(
                    UiConst.Size.BIG_LINE_HEIGHT,
                    borderColor
                ),
                shape = UiConst.Round.SMALL
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = value.icon),
            contentDescription = null,
            modifier = Modifier
                .clip(UiConst.Round.SMALL)
                .size(UiConst.Size.EXIT_BUTTON)
                .background(Color.Magenta.copy(.4f))
        )
    }
}

@Composable
private fun ContainerElement(content: @Composable () -> Unit) {
    Box(modifier = Modifier.padding(UiConst.Padding.SMALL), contentAlignment = Alignment.Center){
        content()
    }
}
