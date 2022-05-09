package dev.alimansour.borutoapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun saveOnBoardingState(completed: Boolean)

    fun getOnBoardingState(): Flow<Boolean>
}