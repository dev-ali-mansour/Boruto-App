package dev.alimansour.borutoapp.domain.usecase.get_selected_hero

import dev.alimansour.borutoapp.domain.model.Hero
import dev.alimansour.borutoapp.domain.repository.Repository
import javax.inject.Inject

class GetSelectedHeroUseCase @Inject constructor(
    private val heroRepository: Repository
) {
    suspend operator fun invoke(heroId: Int): Hero =
        heroRepository.getSelectedHero(heroId = heroId)
}