package com.example.thisappwillbefunny.presentation.fr.select_fun

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.thisappwillbefunny.R
import com.example.thisappwillbefunny.domain.model.FeatureElementModel
import com.example.thisappwillbefunny.presentation.navigation.GET_RANDOM_CAT_ROUTE
import com.example.thisappwillbefunny.presentation.navigation.LIKE_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.navigation.SELECT_ACTIVITY_ROUTE
import com.example.thisappwillbefunny.presentation.navigation.SELECT_INTERNET_STATUS_ROUTE
import com.example.thisappwillbefunny.presentation.ui.elements.Container
import com.example.thisappwillbefunny.presentation.ui.elements.ExitButton
import com.example.thisappwillbefunny.presentation.ui.elements.text.LargeText
import com.example.thisappwillbefunny.presentation.ui.elements.text.TitleText
import com.example.thisappwillbefunny.utils.UiConst
import com.example.thisappwillbefunny.utils.createHorizontalLine
import com.example.thisappwillbefunny.utils.listOfFeature
import kotlin.system.exitProcess

@Composable
fun ChooseActivityFragment(
    viewModel: ChooseActivityViewModel
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize().background(UiConst.Brushes.background)
    ) {
        val (bottomBar) = createRefs()


        BottomBar(
            modifier = Modifier.constrainAs(bottomBar) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            viewModel = viewModel,
        )




    }
}



@Composable private fun BottomBar(
    modifier: Modifier,
    viewModel: ChooseActivityViewModel
) {
    Column(modifier = Modifier
        .fillMaxHeight(.91f)
        .fillMaxWidth()
        .clip(
            RoundedCornerShape(
                topStart = UiConst.Size.EXIT_BUTTON,
                topEnd = UiConst.Size.EXIT_BUTTON
            )
        )
        .background(UiConst.Brushes.MainFragmentElement)
        .then(modifier)
    ) {
        TitleText(
            value = stringResource(id = R.string.app_name) ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = UiConst.Padding.SMALL),
        )
        ColumnFeature(viewModel = viewModel)
    }

}

@Composable
private fun ColumnFeature(
    viewModel: ChooseActivityViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item { ColumnItem(value = listOfFeature[0], onClick = { viewModel.navigate(GET_RANDOM_CAT_ROUTE) })  }
        item { ColumnItem(value = listOfFeature[1], onClick = { viewModel.navigate(SELECT_INTERNET_STATUS_ROUTE) })  }
        item { ColumnItem(value = listOfFeature[2], onClick = { viewModel.navigate(SELECT_ACTIVITY_ROUTE) })  }
        item { ColumnItem(value = listOfFeature[3], onClick = { viewModel.navigate(LIKE_ACTIVITY_ROUTE) })  }
    }
}


@Composable
private fun ColumnItem(
    value: FeatureElementModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(UiConst.Size.WIDTH_TIP_EL)
            .clickable { onClick() },

    ) {
        Box(modifier = Modifier.createHorizontalLine())
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(UiConst.Size.WIDTH_TIP_EL)
                .padding(horizontal = UiConst.Padding.MEDIUM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(3f), contentAlignment = Alignment.Center){
                Icon(
                    painter = painterResource(id = value.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(UiConst.Size.SmallIconBgSize)
                )

            }
            LargeText(
                value = value.title,
                color = value.textColor,
                fontFamily = null,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(6f)
            )
        }
    }
}