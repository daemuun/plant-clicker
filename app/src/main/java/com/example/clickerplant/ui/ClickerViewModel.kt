package com.example.clickerplant.ui

import androidx.lifecycle.ViewModel
import com.example.clickerplant.data.PlantsRepository
import com.example.clickerplant.data.PlantsRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ClickerViewModel(plantsRepository: PlantsRepository = PlantsRepositoryImpl()) : ViewModel() {
    val plants = plantsRepository.getPlants()
    private val _uiState = MutableStateFlow(ClickerUiState(currentPlant = plants[0]))
    val uiState = _uiState.asStateFlow()

    fun onPlantClick() {
        _uiState.update { currentState ->
            currentState.copy(
                countClicks = currentState.countClicks + 1,
                currentRevenue = currentState.currentRevenue + currentState.currentPlant.plantCost
            )
        }
    }

    fun checkUpdateThePlant() {
        if (!checkPlantFinished()) {
            return
        }
        //TODO(обновить состояние (текущее растение))
    }

    private fun checkPlantFinished(): Boolean {
        return uiState.value.countClicks >= uiState.value.currentPlant.thisPlantNeedsCount
    }
}