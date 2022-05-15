package dev.alimansour.borutoapp.data.paging_source

import androidx.paging.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.alimansour.borutoapp.data.local.BorutoDatabase
import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import dev.alimansour.borutoapp.data.remote.FakeBorutoApi
import dev.alimansour.borutoapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class HeroRemoteMediatorTest {

    private lateinit var borutoApi: FakeBorutoApi
    private lateinit var borutoDatabase: BorutoDatabase

    @Before
    fun setUp() {
        borutoApi = FakeBorutoApi()
        borutoDatabase =
            Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                BorutoDatabase::class.java
            ).fallbackToDestructiveMigration()
                .build()

    }

    @After
    fun tearDown() {
        borutoDatabase.clearAllTables()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() =
        runTest {
            val remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            )
            val pagingState = PagingState<Int, HeroEntity>(
                pages = emptyList(),
                anchorPosition = null,
                config = PagingConfig(pageSize = ITEMS_PER_PAGE),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationTrueWhenNoMoreData() =
        runTest {
            borutoApi.clearData()
            val remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            )
            val pagingState = PagingState<Int, HeroEntity>(
                pages = emptyList(),
                anchorPosition = null,
                config = PagingConfig(pageSize = ITEMS_PER_PAGE),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }

    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() =
        runTest {
            borutoApi.throwException()
            val remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            )
            val pagingState = PagingState<Int, HeroEntity>(
                pages = emptyList(),
                anchorPosition = null,
                config = PagingConfig(pageSize = ITEMS_PER_PAGE),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Error)
        }
}