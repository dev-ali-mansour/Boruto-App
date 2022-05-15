package dev.alimansour.borutoapp.data.remote

import androidx.paging.*
import dev.alimansour.borutoapp.data.local.BorutoDatabase
import dev.alimansour.borutoapp.data.local.entity.toModel
import dev.alimansour.borutoapp.data.paging_source.HeroRemoteMediator
import dev.alimansour.borutoapp.data.paging_source.SearchHeroesSource
import dev.alimansour.borutoapp.domain.model.Hero
import dev.alimansour.borutoapp.domain.remote.RemoteDataSource
import dev.alimansour.borutoapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteDataSource {
    private val heroDao = borutoDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> =
        Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ), pagingSourceFactory = { heroDao.getAllHeroes() }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toModel()
            }
        }

    override fun searchHeroes(name: String): Flow<PagingData<Hero>> =
        Pager(config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchHeroesSource(
                    borutoApi = borutoApi,
                    query = name
                )
            }).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toModel()
            }
        }
}