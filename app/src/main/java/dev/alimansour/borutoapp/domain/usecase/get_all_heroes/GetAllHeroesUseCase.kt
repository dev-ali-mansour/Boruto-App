package dev.alimansour.borutoapp.domain.usecase.get_all_heroes

import androidx.paging.PagingData
import dev.alimansour.borutoapp.data.local.entity.HeroEntity
import dev.alimansour.borutoapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllHeroesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<HeroEntity>> = repository.getAllHeroes()
}