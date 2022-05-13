package dev.alimansour.borutoapp.presentation.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    var searchQuery by mutableStateOf("")
        private set

    fun updateSearchQuery(query: String) {
        this.searchQuery = query
    }
}