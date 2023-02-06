package com.example.thisappwillbefunny.utils

import com.example.thisappwillbefunny.domain.model.FeatureElementModel
import com.example.thisappwillbefunny.domain.model.TipInternetStatusModel

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
    TipInternetStatusModel("Tips №1", "Test"),
    TipInternetStatusModel("Tips №23", "Test".repeat(12)),
    TipInternetStatusModel("Tips №64", "Test ".repeat(5)),
    TipInternetStatusModel("Tips №12", "Test ".repeat(23)),
)