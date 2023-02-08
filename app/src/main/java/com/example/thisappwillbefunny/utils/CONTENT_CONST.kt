package com.example.thisappwillbefunny.utils

import androidx.compose.ui.graphics.Color
import com.example.thisappwillbefunny.domain.internet.user_activity.pojo.ActivityPOJO
import com.example.thisappwillbefunny.domain.model.*

val listOfFeature = listOf(
    FeatureElementModel(
        ContentConst.ListOfFeature.First.title,
        ContentConst.ListOfFeature.First.bg,
        ContentConst.ListOfFeature.First.textColor,
    ),
    FeatureElementModel(
        ContentConst.ListOfFeature.Second.title,
        ContentConst.ListOfFeature.Second.bg,
        ContentConst.ListOfFeature.Second.textColor,
    ),
    FeatureElementModel(
        ContentConst.ListOfFeature.Three.title,
        ContentConst.ListOfFeature.Three.bg,
        ContentConst.ListOfFeature.Three.textColor,
    )
)

val listOfTipsInternetStatus = listOf(
    TipInternetStatusModel("Tips №1", "Familiarize yourself with the internet"),
    TipInternetStatusModel("Tips №23", "Enter website addresses carefully"),
    TipInternetStatusModel("Tips №64", "Set a homepage"),
    TipInternetStatusModel("Tips №433","Use Flight Mode when your child is using devices"),
    TipInternetStatusModel("Tips №3"," Be aware of phishing attacks"),
    TipInternetStatusModel("Tips №5"," Use two-factor authentication"),
    TipInternetStatusModel("Tips №53"," Use Biometric identification"),
    TipInternetStatusModel("Tips №83"," Use a VPN for extra security"),
    TipInternetStatusModel("Tips №21","  Install anti-virus software and keep it up to date"),
)

val emptyRequestActivityModel = RequestActivityModel(
    activityPOJO = ActivityPOJO(0.0,"","","",0,0.0,""),
    accessibleModel = ActivityItemDescModel(UiConst.Brushes.MIAKA, 0, Color.Transparent, "", ""),
    participants = ActivityItemDescModel(UiConst.Brushes.MIAKA, 0, Color.Transparent, "", ""),
    pricing = ActivityItemDescModel(UiConst.Brushes.MIAKA, 0, Color.Transparent, "", ""),
)