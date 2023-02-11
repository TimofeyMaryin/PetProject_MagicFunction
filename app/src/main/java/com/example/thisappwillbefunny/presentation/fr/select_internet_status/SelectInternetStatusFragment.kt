package com.example.thisappwillbefunny.presentation.fr.select_internet_status

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.airbnb.lottie.compose.*
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.use_cases.GetCatsDescInternetStatus
import com.example.thisappwillbefunny.presentation.ui.elements.AppButton
import com.example.thisappwillbefunny.domain.model.CatsInternetStatusModel
import com.example.thisappwillbefunny.domain.model.TipInternetStatusModel
import com.example.thisappwillbefunny.presentation.fr.tip_swipe.TipSwipeRight
import com.example.thisappwillbefunny.presentation.ui.elements.CarouselTips
import com.example.thisappwillbefunny.presentation.ui.elements.Container
import com.example.thisappwillbefunny.presentation.ui.elements.LoadingShimmerEffect
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.elements.text.MediumText
import com.example.thisappwillbefunny.presentation.ui.elements.text.SmallText
import com.example.thisappwillbefunny.presentation.ui.font.Raleway
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.listOfTipsInternetStatus
import com.example.thisappwillbefunny.utils.swipeRightToReturn
import kotlinx.coroutines.delay
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectInternetStatusFragment(
    viewModel: SelectInternetStatusViewModel,
    navController: NavController
) {
    var isShowTips by remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (content, topBar, tips) = createRefs()
        var showFullSizeImage by remember { mutableStateOf(false) }
        var currentUrl by remember { mutableStateOf("") }




         LazyColumn(
            content = {
                stickyHeader {
                    TopBarInternetStatusFragment(value = listOfTipsInternetStatus[Random.nextInt(0, listOfTipsInternetStatus.size)], modifier = Modifier)
                }
                items(GetCatsDescInternetStatus.internetStatusCodes.size) {
                    CatsInternetStatusItem(
                        value = CatsInternetStatusModel(
                            imageUrl = GetCatsDescInternetStatus.execute(GetCatsDescInternetStatus.internetStatusCodes[it]),
                            statusCode = GetCatsDescInternetStatus.internetStatusCodes[it]
                        ),
                        onClick = {
                            currentUrl = GetCatsDescInternetStatus.execute(GetCatsDescInternetStatus.internetStatusCodes[it])
                            showFullSizeImage = true
                        }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(content) {
                    top.linkTo(topBar.bottom, margin = UiConst.Padding.BETWEEN_ELEMENT)
                }
                .swipeRightToReturn { navController.popBackStack() }
        )

        if (showFullSizeImage){
            ShowImageFullSize(
                url = currentUrl,
                viewModel = viewModel,
                onBack = { showFullSizeImage = false }
            )
        }

        if(!isShowTips){
            TipSwipeRight(modifier = Modifier.constrainAs(tips) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }) {
                isShowTips = true
            }
        }
    }
}
