package dev.alimansour.borutoapp.domain.remote

import androidx.paging.PagingData
import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<HeroEntity>>
    fun searchHeroes(name: String): Flow<PagingData<HeroEntity>>
}