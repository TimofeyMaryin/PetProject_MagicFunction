package com.example.thisappwillbefunny.presentation.fr.get_random_cat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.presentation.fr.tip_swipe.TipSwipeRight
import com.example.thisappwillbefunny.presentation.ui.elements.AppButton
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.swipeRightToReturn

@Composable
fun GetRandomCatFragment(
    viewModel: GetRandomCatViewModel,
    navController: NavController
) {
    var isShowTips by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize().swipeRightToReturn { navController.popBackStack() }
    ) {
        val (image, buttonPlace, tips) = createRefs()

        var result by remember { mutableStateOf("") }
        var changeImage by remember { mutableStateOf(1L) }


        SubcomposeAsyncImage(
            model = result,
            contentDescription = null,
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(UiConst.Size.BtnIconSize),
                    contentScale = ContentScale.FillHeight
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(.4f)))
                SubcomposeAsyncImageContent(
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        BottomButtonPlace(
            modifier = Modifier.constrainAs(buttonPlace) {
                bottom.linkTo(parent.bottom)
            },
            onReload = { changeImage++ },
            onDownload = { viewModel.popBackStack() }
        )

        if (!isShowTips){
            TipSwipeRight(
                modifier = Modifier.constrainAs(tips){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)

                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                isShowTips = true
            }
        }




        LaunchedEffect(
            key1 = changeImage,
            block = {
                result = viewModel.getRandomCat()
            },
        )

    }
}

@Composable
private fun BottomButtonPlace(
    modifier: Modifier,
    onReload: () -> Unit,
    onDownload: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppButton(
            modifier = Modifier.weight(1f),
            text = "Generate",
            onClick = { onReload() },
            bgColor = Color.Cyan,
            icon = R.drawable.reload,
            contentColor = Color.White
        )
        AppButton(
            modifier = Modifier.weight(1f),
            text = "Download",
            onClick = { onDownload() },
            bgColor = Color.DarkGray,
            icon = R.drawable.download,
            contentColor = Color.White
        )
    }
}