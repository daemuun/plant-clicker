package com.example.clickerplant.data

import com.example.clickerplant.model.Plant

interface PlantsRepository {
    fun getPlants(): List<Plant>
}