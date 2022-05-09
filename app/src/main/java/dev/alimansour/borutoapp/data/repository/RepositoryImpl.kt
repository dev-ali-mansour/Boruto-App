package dev.alimansour.borutoapp.data.repository

import androidx.paging.PagingData
import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import dev.alimansour.borutoapp.domain.local.DataStoreOperations
import dev.alimansour.borutoapp.domain.remote.RemoteDataSource
import dev.alimansour.borutoapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val datastore: DataStoreOperations
) : Repository {

    override suspend fun saveOnBoardingState(completed: Boolean) {
        datastore.saveOnBoardingState(completed = completed)
    }

    override fun getOnBoardingState(): Flow<Boolean> = datastore.getOnBoardingState()

    override fun getAllHeroes(): Flow<PagingData<HeroEntity>> = remoteDataSource.getAllHeroes()

    override fun searchHeroes(name: String): Flow<PagingData<HeroEntity>> {
        TODO("Not yet implemented")
    }
}