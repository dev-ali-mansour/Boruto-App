package dev.alimansour.borutoapp.data.remote

import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import dev.alimansour.borutoapp.data.remote.response.ApiResponse

class FakeBorutoApi : BorutoApi {

    private val heroes = listOf(
        HeroEntity(
            id = 1,
            name = "Sasuke",
            image = "",
            about = "",
            rating = 5.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        HeroEntity(
            id = 2,
            name = "Naruto",
            image = "",
            about = "",
            rating = 5.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        HeroEntity(
            id = 3,
            name = "Sakura",
            image = "",
            about = "",
            rating = 5.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        )
    )

    override suspend fun getAllHeroes(page: Int, limit: Int): ApiResponse =
        ApiResponse(success = false)

    override suspend fun searchHeroes(name: String): ApiResponse =
        ApiResponse(
            success = true,
            message = "Ok",
            heroes = findHeroes(name = name),
        )

    private fun findHeroes(name: String): List<HeroEntity> {
        val founded = mutableListOf<HeroEntity>()
        return when {
            name.isNotEmpty() -> {
                heroes.forEach { hero ->
                    if (hero.name.lowercase().contains(name.lowercase())) founded.add(hero)
                }
                founded
            }
            else -> emptyList()
        }
    }
}