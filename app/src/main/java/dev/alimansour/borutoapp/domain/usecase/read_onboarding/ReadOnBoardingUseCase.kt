package dev.alimansour.borutoapp.domain.usecase.read_onboarding

import dev.alimansour.borutoapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadOnBoardingUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<Boolean> = repository.getOnBoardingState()
}