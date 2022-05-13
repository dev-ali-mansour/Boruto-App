package dev.alimansour.borutoapp.data.repository

import androidx.paging.PagingData
import dev.alimansour.borutoapp.domain.local.DataStoreOperations
import dev.alimansour.borutoapp.domain.local.LocalDataSource
import dev.alimansour.borutoapp.domain.model.Hero
import dev.alimansour.borutoapp.domain.remote.RemoteDataSource
import dev.alimansour.borutoapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val datastore: DataStoreOperations
) : Repository {

    override suspend fun saveOnBoardingState(completed: Boolean) {
        datastore.saveOnBoardingState(completed = completed)
    }

    override fun getOnBoardingState(): Flow<Boolean> =
        datastore.getOnBoardingState()

    override fun getAllHeroes(): Flow<PagingData<Hero>> =
        remote.getAllHeroes()

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> =
        remote.searchHeroes(name = query)

    override suspend fun getSelectedHero(heroId: Int): Hero =
        local.getSelectedHero(heroId = heroId)
}