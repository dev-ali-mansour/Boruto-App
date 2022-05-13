package dev.alimansour.borutoapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import dev.alimansour.borutoapp.data.remote.BorutoApi
import javax.inject.Inject

class SearchHeroesResource @Inject constructor(
    private val borutoApi: BorutoApi,
    private val query: String
) : PagingSource<Int, HeroEntity>() {

    override fun getRefreshKey(state: PagingState<Int, HeroEntity>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HeroEntity> {
        runCatching {
            val apiResponse = borutoApi.searchHeroes(name = query)
            val heroes = apiResponse.heroes
            if (heroes.isNotEmpty()) {
                return LoadResult.Page(
                    data = heroes,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                return LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        }.getOrElse {
            return LoadResult.Error(it)
        }
    }
}