package dev.alimansour.borutoapp.data.paging_source

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import dev.alimansour.borutoapp.data.remote.BorutoApi
import dev.alimansour.borutoapp.data.remote.FakeBorutoApi
import dev.alimansour.borutoapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


@ExperimentalCoroutinesApi
internal class SearchHeroesSourceTest {

    private lateinit var borutoApi: BorutoApi
    private lateinit var heroes: List<HeroEntity>

    @Before
    fun setUp() {
        borutoApi = FakeBorutoApi()
        heroes = listOf(
            HeroEntity(
                id = 1,
                name = "Sasuke",
                image = "",
                about = "",
                rating = 5.0,
                power = 0,
                month = "",
                day = "",
                family = listOf(),
                abilities = listOf(),
                natureTypes = listOf()
            ),
            HeroEntity(
                id = 2,
                name = "Naruto",
                image = "",
                about = "",
                rating = 5.0,
                power = 0,
                month = "",
                day = "",
                family = listOf(),
                abilities = listOf(),
                natureTypes = listOf()
            ),
            HeroEntity(
                id = 3,
                name = "Sakura",
                image = "",
                about = "",
                rating = 5.0,
                power = 0,
                month = "",
                day = "",
                family = listOf(),
                abilities = listOf(),
                natureTypes = listOf()
            )
        )
    }

    @Test
    fun `Search api with existing hero name, expect a single hero result, assert LoadResult_Page`() =
        runTest {
            val heroSource = SearchHeroesSource(borutoApi = borutoApi, query = "Sasuke")
            assertEquals<LoadResult<Int, HeroEntity>>(
                expected = LoadResult.Page(
                    data = listOf(heroes.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = ITEMS_PER_PAGE,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with a hero name, expect multiple hero result, assert LoadResult_Page`() =
        runTest {
            val heroSource = SearchHeroesSource(borutoApi = borutoApi, query = "Sa")
            assertEquals<LoadResult<Int, HeroEntity>>(
                expected = LoadResult.Page(
                    data = listOf(heroes.first(), heroes[2]),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = ITEMS_PER_PAGE,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with empty hero name, assert empty heroes list and LoadResult_Page`() =
        runTest {
            val heroSource = SearchHeroesSource(borutoApi = borutoApi, query = "")
            val loadResult = heroSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = ITEMS_PER_PAGE,
                    placeholdersEnabled = false
                )
            )

            val result = borutoApi.searchHeroes(name = "")

            assertTrue { result.heroes.isEmpty() }
            assertTrue { loadResult is LoadResult.Page }
        }

    @Test
    fun `Search api with non existing hero name, assert empty heroes list and LoadResult_Page`() =
        runTest {
            val heroSource = SearchHeroesSource(borutoApi = borutoApi, query = "Unknown")
            val loadResult = heroSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = ITEMS_PER_PAGE,
                    placeholdersEnabled = false
                )
            )

            val result = borutoApi.searchHeroes(name = "Unknown")

            assertTrue { result.heroes.isEmpty() }
            assertTrue { loadResult is LoadResult.Page }
        }
}