package com.cmp.bookapp.book.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cmp.bookapp.book.domain.model.Book
import com.cmp.bookapp.book.presentation.bookState.BookListAction
import com.cmp.bookapp.book.presentation.bookState.BookListState
import com.cmp.bookapp.book.presentation.bookState.BookListViewModel
import com.cmp.bookapp.book.presentation.ui.components.BookListItem
import com.cmp.bookapp.book.presentation.ui.components.BookSearchBar
import com.cmp.bookapp.core.presentation.Colors.DarkBlue
import com.cmp.bookapp.core.presentation.Colors.DesertWhite
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel = koinViewModel(),
    onBookClick: (Book) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookListScreen(
        state = state, onAction = { action ->
            when (action) {
                is BookListAction.OnBookClick -> onBookClick(action.book)
                is BookListAction.OnSearchQueryChange -> {}
                is BookListAction.OnTabSelected -> {}
            }
            viewModel.onAction(action)

        })
}


@Composable
private fun BookListScreen(state: BookListState, onAction: (BookListAction) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier.fillMaxSize()
            .background(DarkBlue)
            .statusBarsPadding()
    ) {
        BookSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(BookListAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )

        Surface(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            color = DesertWhite,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) { }
    }
}

@Composable
private fun BookList(
    bookList: List<Book>,
    onBookClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(bookList, key = { it.id }) { item ->
            BookListItem(
                book = item,
                modifier = Modifier
                    .widthIn(max = 700.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                onClick = {
                    onBookClick(item)
                }
            )
        }
    }
}