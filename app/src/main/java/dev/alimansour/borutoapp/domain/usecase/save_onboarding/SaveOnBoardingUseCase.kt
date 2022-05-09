package dev.alimansour.borutoapp.domain.usecase.save_onboarding

import dev.alimansour.borutoapp.domain.repository.Repository
import javax.inject.Inject

class SaveOnBoardingUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }

}