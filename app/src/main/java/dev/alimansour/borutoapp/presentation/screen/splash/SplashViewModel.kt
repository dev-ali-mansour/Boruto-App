package dev.alimansour.borutoapp.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alimansour.borutoapp.domain.usecase.read_onboarding.ReadOnBoardingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val readOnBoardingUseCase: ReadOnBoardingUseCase
) : ViewModel() {
    private val _onBoardingCompleted = MutableStateFlow<Boolean>(false)
    val onBoardingCompleted: StateFlow<Boolean> = _onBoardingCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingCompleted.value =
                readOnBoardingUseCase().stateIn(viewModelScope).value
        }
    }

}