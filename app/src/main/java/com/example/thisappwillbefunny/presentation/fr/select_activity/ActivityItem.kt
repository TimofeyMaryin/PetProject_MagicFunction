package com.example.thisappwillbefunny.presentation.fr.select_activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.thisappwillbefunny.domain.model.ActivityItemDescModel
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.presentation.ui.font.Raleway
import com.example.thisappwillbefunny.utils.UiConst

@Composable
fun ActivityItem(
    modifier: Modifier,
    viewModel: SelectActivityViewModel,
    index: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(.97f)
            .clip(UiConst.Round.SMALL)
            .background(UiConst.Brushes.ActivityItem)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MediumText(
            value = viewModel.currentRequest[index].activityPOJO.activity,
            maxLines = 1,
            modifier = Modifier.weight(1f).padding(start = UiConst.Padding.SMALL),
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold
        )
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            ActivityItemDesc(model = viewModel.activityRepo.getAccessibility(viewModel.currentRequest[index].activityPOJO.accessibility.toFloat()))
            ActivityItemDesc(model = viewModel.activityRepo.getParticipants(viewModel.currentRequest[index].activityPOJO.participants))
            ActivityItemDesc(model = viewModel.activityRepo.getPrice(viewModel.currentRequest[index].activityPOJO.price.toFloat()))
        }
    }
}


@Composable
private fun ActivityItemDesc(
    model: ActivityItemDescModel
) {
    Box(
        modifier = Modifier
            .size(UiConst.Size.ACTIVITY_ITEM_SIZE)
            .padding(UiConst.Padding.SMALL)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.clip(UiConst.Round.NORMAL).background(model.bg).size(UiConst.Size.ACTIVITY_ITEM_SIZE))
        Icon(
            painter = painterResource(id = model.icon),
            contentDescription = null,
            tint = model.iconColor,
            modifier = Modifier.size(UiConst.Size.ACTIVITY_ITEM_SIZE / 2)
        )
    }

}