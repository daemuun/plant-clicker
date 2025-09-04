package com.example.clickerplant.ui

import androidx.lifecycle.ViewModel
import com.example.clickerplant.data.PlantsRepository
import com.example.clickerplant.data.PlantsRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ClickerViewModel(plantsRepository: PlantsRepository = PlantsRepositoryImpl()) : ViewModel() {
    val plants = plantsRepository.getPlants()
    private val _uiState = MutableStateFlow(ClickerUiState(currentPlantIndex = 0))
    val uiState = _uiState.asStateFlow()

    fun onPlantClick() {
        _uiState.update { currentState ->
            currentState.copy(
                countClicks = currentState.countClicks.inc(),
                currentRevenue = currentState.currentRevenue + plants[currentState.currentPlantIndex].plantCost
            )
        }
    }

    fun checkUpdateThePlant() {
        if (!checkPlantFinished()) {
            return
        }
        _uiState.update { currentState ->
            currentState.copy(
                currentPlantIndex = currentState.currentPlantIndex.inc()
            )
        }
    }

    private fun checkPlantFinished(): Boolean {
        return uiState.value.countClicks >= plants[uiState.value.currentPlantIndex].countThisPlantTaps
                && uiState.value.currentPlantIndex < plants.lastIndex
    }
}