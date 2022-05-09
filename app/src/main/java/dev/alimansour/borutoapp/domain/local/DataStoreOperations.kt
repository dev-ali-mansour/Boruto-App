package dev.alimansour.borutoapp.domain.local

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun saveOnBoardingState(completed: Boolean)

    fun getOnBoardingState(): Flow<Boolean>
}