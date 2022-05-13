package dev.alimansour.borutoapp.presentation.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val heroes = searchViewModel.searchHeroes.collectAsLazyPagingItems()

    Scaffold(topBar = {
        SearchTopBar(
            text = searchViewModel.searchQuery,
            onTextChanged = {
                searchViewModel.updateSearchQuery(it)
            },
            onSearchClicked = {
                searchViewModel.searchHeroes(query = it)
            },
            onCloseClicked = {
                navController.popBackStack()
            })
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }
    }
}