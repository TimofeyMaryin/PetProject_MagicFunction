package com.example.thisappwillbefunny.presentation.fr.select_activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.thisappwillbefunny.domain.internet.user_activity.pojo.ActivityPOJO
import com.example.thisappwillbefunny.domain.model.ActivityItemDescModel
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.presentation.ui.elements.text.SmallText
import com.example.thisappwillbefunny.presentation.ui.font.Raleway
import com.example.thisappwillbefunny.utils.UiConst

@Composable
fun ActivityItem(
    activityName: String,
    modifier: Modifier,

    accessibilityModel: ActivityItemDescModel,
    participantsModel: ActivityItemDescModel,
    priceModel: ActivityItemDescModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(.97f)
            .clip(UiConst.Round.SMALL)
            .background(Color.White.copy(.7f))
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MediumText(
            value = activityName,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            fontFamily = Raleway,
            fontWeight = FontWeight.Bold
        )
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            ActivityItemDesc(model = accessibilityModel)
            ActivityItemDesc(model = participantsModel)
            ActivityItemDesc(model = priceModel)
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