package com.example.clickerplant.data

import com.example.clickerplant.R
import com.example.clickerplant.model.Plant

class PlantsRepositoryImpl() : PlantsRepository {
    override fun getPlants(): List<Plant> {
        return listOf(
            Plant(R.drawable.cucumber, R.string.cucumber, 20, 0),
            Plant(R.drawable.tomato, R.string.tomato, 40, 20)
        ).sortedBy { it.thisPlantNeedsCount }
    }
}