package com.example.clickerplant.data

import com.example.clickerplant.R
import com.example.clickerplant.model.Plant

class PlantsRepositoryImpl : PlantsRepository {
    override fun getPlants(): List<Plant> {
        return listOf(
            Plant(R.drawable.cucumber, R.string.cucumber, 20, 20),
            Plant(R.drawable.tomato, R.string.tomato, 40, 40),
            Plant(R.drawable.garlic, R.string.garlic, 200, 150),
            Plant(R.drawable.chili, R.string.chili, 500, 240),
            Plant(R.drawable.potato, R.string.potato, 1200, 500),
            Plant(R.drawable.onion, R.string.onion, 2000, 1000),
        ).sortedBy { it.countThisPlantTaps }
    }
}