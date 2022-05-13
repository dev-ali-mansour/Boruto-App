package dev.alimansour.borutoapp.presentation.screen.search

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import dev.alimansour.borutoapp.R
import dev.alimansour.borutoapp.presentation.theme.TOP_BAR_HEIGHT
import dev.alimansour.borutoapp.presentation.theme.topAppBarBackgroundColor
import dev.alimansour.borutoapp.presentation.theme.topAppBarContentColor

@Composable
fun SearchTopBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {

}

@Composable
fun SearchWidget(
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChanged(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    text = stringResource(R.string.search_here),
                    color = topAppBarContentColor
                )
            },
            textStyle = TextStyle(
                color = topAppBarContentColor
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search_icon),
                        tint = topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) onTextChanged("")
                        else onCloseClicked()
                    }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close_icon),
                        tint = topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchClicked(text) }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = topAppBarContentColor
            )
        )
    }
}

@Preview
@Composable
fun SearchWidgetPreview() {
    SearchWidget(
        text = "",
        onTextChanged = {},
        onSearchClicked = {},
        onCloseClicked = {}
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchWidgetDarkPreview() {
    SearchWidget(
        text = "",
        onTextChanged = {},
        onSearchClicked = {},
        onCloseClicked = {}
    )
}