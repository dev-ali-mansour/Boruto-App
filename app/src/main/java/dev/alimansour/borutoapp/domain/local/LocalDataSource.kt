package dev.alimansour.borutoapp.domain.local

import dev.alimansour.borutoapp.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}