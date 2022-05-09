package dev.alimansour.borutoapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.alimansour.borutoapp.data.local.BorutoDatabase
import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import dev.alimansour.borutoapp.data.paging_source.HeroRemoteMediator
import dev.alimansour.borutoapp.domain.remote.RemoteDataSource
import dev.alimansour.borutoapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteDataSource {
    private val heroDao = borutoDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<HeroEntity>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ), pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(name: String): Flow<PagingData<HeroEntity>> {
        TODO("Not yet implemented")
    }
}