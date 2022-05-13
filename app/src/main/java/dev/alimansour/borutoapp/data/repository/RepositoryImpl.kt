package dev.alimansour.borutoapp.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import dev.alimansour.borutoapp.data.local.entity.toModel
import dev.alimansour.borutoapp.domain.local.DataStoreOperations
import dev.alimansour.borutoapp.domain.model.Hero
import dev.alimansour.borutoapp.domain.remote.RemoteDataSource
import dev.alimansour.borutoapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val datastore: DataStoreOperations
) : Repository {

    override suspend fun saveOnBoardingState(completed: Boolean) {
        datastore.saveOnBoardingState(completed = completed)
    }

    override fun getOnBoardingState(): Flow<Boolean> =
        datastore.getOnBoardingState()

    override fun getAllHeroes(): Flow<PagingData<Hero>> =
        remoteDataSource.getAllHeroes().map { pagingData ->
            pagingData.map { entity ->
                entity.toModel()
            }
        }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> =
        remoteDataSource.searchHeroes(name = query).map { pagingData ->
            pagingData.map { entity ->
                entity.toModel()
            }
        }
}