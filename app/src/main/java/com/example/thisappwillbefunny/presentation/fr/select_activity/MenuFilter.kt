package com.example.thisappwillbefunny.presentation.fr.select_activity

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.thisappwillbefunny.domain.model.FilterElementModel
import com.example.thisappwillbefunny.utils.UiConst

@Deprecated("Я не знаю, как это сделать. Doesn`t work.")
@Composable
fun MenuFilter(viewModel: SelectActivityViewModel) {


    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){

        LazyRow(
            modifier = Modifier
                .clip(UiConst.Round.SMALL)
                .fillMaxWidth(.95f)
                .background(UiConst.Brushes.FilterBg),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            items(viewModel.getFilterElements().size) {
                ContainerElement {
                    MenuFilterItem(value = viewModel.getFilterElements()[it]) {
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
            .size(UiConst.Size.SmallIconBorder)
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
        Box(
            modifier = Modifier.clip(UiConst.Round.SMALL).size(UiConst.Size.SmallIconBgSize).background(value.bg),
            contentAlignment = Alignment.Center
        ){

            Icon(
                painter = painterResource(id = value.icon),
                contentDescription = null,
                modifier = Modifier.size(UiConst.Size.SmallIcon)
            )
        }
    }
}

@Composable
private fun ContainerElement(content: @Composable () -> Unit) {
    Box(modifier = Modifier.padding(UiConst.Padding.SMALL), contentAlignment = Alignment.Center){
        content()
    }
}
