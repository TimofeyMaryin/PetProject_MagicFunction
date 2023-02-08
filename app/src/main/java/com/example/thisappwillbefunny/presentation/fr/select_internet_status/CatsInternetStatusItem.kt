package com.example.thisappwillbefunny.presentation.fr.select_internet_status

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.thisappwillbefunny.domain.model.CatsInternetStatusModel
import com.example.thisappwillbefunny.presentation.ui.elements.LoadingShimmerEffect
import com.example.thisappwillbefunny.utils.UiConst

@Composable
fun CatsInternetStatusItem(
    value: CatsInternetStatusModel,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = UiConst.Padding.MEDIUM),
        contentAlignment = Alignment.Center
    ){

        SubcomposeAsyncImage(
            model = value.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val state = painter.state

            if (!(state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error)){
                SubcomposeAsyncImageContent(modifier = Modifier.clickable { onClick()})
            } else {
                LoadingShimmerEffect(modifier = Modifier.fillMaxSize())
            }
        }
    }

}