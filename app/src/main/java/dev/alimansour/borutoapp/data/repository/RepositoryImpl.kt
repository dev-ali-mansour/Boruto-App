package dev.alimansour.borutoapp.data.repository

import dev.alimansour.borutoapp.domain.local.DataStoreOperations
import dev.alimansour.borutoapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl(
    private val datastore: DataStoreOperations
) : Repository {

    override suspend fun saveOnBoardingState(completed: Boolean) {
        datastore.saveOnBoardingState(completed = completed)
    }

    override fun getOnBoardingState(): Flow<Boolean> = datastore.getOnBoardingState()
}