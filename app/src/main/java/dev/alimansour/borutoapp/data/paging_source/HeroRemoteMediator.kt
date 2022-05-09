package dev.alimansour.borutoapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.alimansour.borutoapp.data.local.BorutoDatabase
import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import dev.alimansour.borutoapp.data.local.entity.HeroRemoteKeys
import dev.alimansour.borutoapp.data.remote.BorutoApi
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteMediator<Int, HeroEntity>() {
    private val heroDao = borutoDatabase.heroDao()
    private val heroRemoteKeysDao = borutoDatabase.heroRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HeroEntity>
    ): MediatorResult = runCatching {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeysForFirstItem(state)
                val prevPage = remoteKeys?.prevPage ?: return@runCatching MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                prevPage
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeysForLastItem(state)
                val nextPage = remoteKeys?.nextPage ?: return@runCatching MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                nextPage
            }
        }
        val response = borutoApi.getAllHeroes()
        if (response.heroes.isNotEmpty()) {
            borutoDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    heroDao.deleteAllHeroes()
                    heroRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.heroes.map { heroEntity ->
                    HeroRemoteKeys(
                        id = heroEntity.id,
                        prevPage = response.prevPage,
                        nextPage = response.nextPage
                    )
                }
                heroRemoteKeysDao.addAllRemoteKeys(heroRemoteKeys = keys)
                heroDao.addHeroes(heroes = response.heroes)
            }
        }
        MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
    }.getOrElse { throwable ->
        MediatorResult.Error(throwable)
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, HeroEntity>
    ): HeroRemoteKeys? =
        state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { heroEntity ->
                heroRemoteKeysDao.getRemoteKeys(heroId = heroEntity.id)
            }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, HeroEntity>
    ): HeroRemoteKeys? =
        state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { heroEntity ->
                heroRemoteKeysDao.getRemoteKeys(heroId = heroEntity.id)
            }

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, HeroEntity>
    ): HeroRemoteKeys? = state.anchorPosition?.let { position ->
        state.closestItemToPosition(position)?.id?.let { id ->
            heroRemoteKeysDao.getRemoteKeys(heroId = id)
        }
    }
}