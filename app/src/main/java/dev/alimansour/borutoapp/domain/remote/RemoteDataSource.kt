package dev.alimansour.borutoapp.domain.remote

import androidx.paging.PagingData
import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import dev.alimansour.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(name: String): Flow<PagingData<Hero>>
}