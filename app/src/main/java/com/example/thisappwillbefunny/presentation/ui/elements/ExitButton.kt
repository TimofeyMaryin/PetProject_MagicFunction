package com.example.thisappwillbefunny.presentation.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.thisappwillbefunny.utils.UiConst

@Composable
fun ExitButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(UiConst.Size.EXIT_BUTTON)
            .clip(UiConst.Round.SMALL)
            .clickable { onClick() }
            .background(UiConst.Color.EXIT_BUTTON.copy(.4f))
            .then(modifier),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Default.ExitToApp,
            contentDescription = null,
            tint = UiConst.Color.EXIT_BUTTON_ICON
        )
    }
}