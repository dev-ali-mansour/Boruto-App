package dev.alimansour.borutoapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alimansour.borutoapp.domain.usecase.get_all_heroes.GetAllHeroesUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllHeroesUseCase: GetAllHeroesUseCase
) : ViewModel() {
    val getAllHeroes = getAllHeroesUseCase()
}