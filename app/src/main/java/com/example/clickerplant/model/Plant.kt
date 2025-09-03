package com.example.clickerplant.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Plant(
    @DrawableRes val plantImageId: Int,
    @StringRes val plantNameId: Int,
    val plantCost: Int,
    val thisPlantNeedsCount: Int,
)