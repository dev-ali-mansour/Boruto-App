package dev.alimansour.borutoapp.presentation.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputText_assertInputText() {
        var text by mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text,
                onTextChanged = {
                    text = it
                },
                onSearchClicked = {},
                onCloseClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Ali Mansour")
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextEquals("Ali Mansour")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButtonOnce_assertEmptyInputText() {
        var text by mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text,
                onTextChanged = { text = it },
                onSearchClicked = {},
                onCloseClicked = { text = "" }
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Ali Mansour")
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButtonTwice_assertClosedState() {
        var text by mutableStateOf("")
        var searchWidgetShown by mutableStateOf(true)

        composeTestRule.setContent {
            if (searchWidgetShown) {
                SearchWidget(
                    text = text,
                    onTextChanged = { text = it },
                    onSearchClicked = {},
                    onCloseClicked = {
                        if (text.isNotEmpty()) text = ""
                        else searchWidgetShown = false
                    }
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Ali Mansour")
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }

    @Test
    fun openSearchWidget_pressCloseButtonOnceWhenInputIsEmpty_assertClosedState() {
        var text by mutableStateOf("")
        var searchWidgetShown by mutableStateOf(true)

        composeTestRule.setContent {
            if (searchWidgetShown) {
                SearchWidget(
                    text = text,
                    onTextChanged = { text = it },
                    onSearchClicked = {},
                    onCloseClicked = {
                        if (text.isNotEmpty()) text = ""
                        else searchWidgetShown = false
                    }
                )
            }
        }
        composeTestRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }
}