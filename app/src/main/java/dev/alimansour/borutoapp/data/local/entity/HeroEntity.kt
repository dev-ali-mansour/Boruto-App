package dev.alimansour.borutoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.alimansour.borutoapp.domain.model.Hero
import dev.alimansour.borutoapp.util.Constants.HERO_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = HERO_DATABASE_TABLE)
data class HeroEntity(
    @PrimaryKey(autoGenerate = false)
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

fun HeroEntity.toModel(): Hero = Hero(
    id, name, image, about, rating, power, month, day, family, abilities, natureTypes
)

fun Hero.toEntity(): HeroEntity = HeroEntity(
    id, name, image, about, rating, power, month, day, family, abilities, natureTypes
)
