package dev.alimansour.borutoapp.domain.usecase.search_heroes

import androidx.paging.PagingData
import dev.alimansour.borutoapp.domain.model.Hero
import dev.alimansour.borutoapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchHeroesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Hero>> =
        repository.searchHeroes(query = query)
}