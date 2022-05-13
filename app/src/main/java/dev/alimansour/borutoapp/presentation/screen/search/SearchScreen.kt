package dev.alimansour.borutoapp.presentation.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen() {
    Scaffold(topBar = {
        SearchTopBar(
            text = "",
            onTextChanged = {},
            onSearchClicked = {},
            onCloseClicked = {})
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }
    }
}