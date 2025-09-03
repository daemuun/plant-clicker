package com.example.clickerplant.ui

import com.example.clickerplant.model.Plant

data class ClickerUiState(
    val countClicks: Int = 0,
    val currentRevenue: Int = 0,
    val currentPlant: Plant
)

