package dev.alimansour.borutoapp.domain.repository

import androidx.paging.PagingData
import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import dev.alimansour.borutoapp.domain.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun saveOnBoardingState(completed: Boolean)
    fun getOnBoardingState(): Flow<Boolean>
    fun getAllHeroes(): Flow<PagingData<HeroEntity>>
    fun searchHeroes(name: String): Flow<PagingData<HeroEntity>>
}