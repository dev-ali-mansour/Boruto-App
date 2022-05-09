package dev.alimansour.borutoapp.domain.model

import dev.alimansour.borutoapp.data.local.entity.HeroEntity


data class Hero(
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>
)
