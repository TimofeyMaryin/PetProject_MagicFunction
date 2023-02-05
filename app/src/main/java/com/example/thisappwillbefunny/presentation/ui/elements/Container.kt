package com.example.thisappwillbefunny.presentation.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.thisappwillbefunny.utils.UiConst

@Composable
fun Container(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UiConst.Padding.CONTAINER)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        content()
    }

}