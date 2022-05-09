package dev.alimansour.borutoapp.presentation.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alimansour.borutoapp.domain.usecase.read_onboarding.ReadOnBoardingUseCase
import dev.alimansour.borutoapp.domain.usecase.save_onboarding.SaveOnBoardingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val saveOnBoardingUseCase: SaveOnBoardingUseCase
) : ViewModel() {

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveOnBoardingUseCase(completed = completed)
        }
    }

}