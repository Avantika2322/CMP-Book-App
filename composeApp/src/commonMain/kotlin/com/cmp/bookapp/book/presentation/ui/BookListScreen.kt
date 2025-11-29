package com.cmp.bookapp.book.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bookapp.composeapp.generated.resources.Res
import bookapp.composeapp.generated.resources.close_hint
import bookapp.composeapp.generated.resources.favorites
import bookapp.composeapp.generated.resources.no_result
import bookapp.composeapp.generated.resources.search_result
import com.cmp.bookapp.book.domain.model.Book
import com.cmp.bookapp.book.presentation.bookState.BookListAction
import com.cmp.bookapp.book.presentation.bookState.BookListState
import com.cmp.bookapp.book.presentation.bookState.BookListViewModel
import com.cmp.bookapp.book.presentation.ui.components.BookListItem
import com.cmp.bookapp.book.presentation.ui.components.BookSearchBar
import com.cmp.bookapp.core.presentation.Colors.DarkBlue
import com.cmp.bookapp.core.presentation.Colors.DesertWhite
import com.cmp.bookapp.core.presentation.Colors.SandYellow
import org.jetbrains.compose.resources.stringResource
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
    val pagerState = rememberPagerState { 2 }
    val searchResultListState = rememberLazyListState()
    val favoriteListState = rememberLazyListState()

    LaunchedEffect(state.searchResults){
        searchResultListState.animateScrollToItem(0)
    }

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
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    modifier = Modifier.padding(vertical = 12.dp)
                        .widthIn(max = 700.dp)
                        .fillMaxWidth(),
                    containerColor = DesertWhite,
                    indicator = { tab ->
                        TabRowDefaults.SecondaryIndicator(
                            color = SandYellow,
                            modifier = Modifier.tabIndicatorOffset(tab[state.selectedTabIndex])
                        )
                    }
                ) {
                    Tab(
                        selected = state.selectedTabIndex == 0,
                        onClick = {
                            onAction(BookListAction.OnTabSelected(0))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.search_result),
                            modifier = Modifier.padding(vertical = 12.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Tab(
                        selected = state.selectedTabIndex == 1,
                        onClick = {
                            onAction(BookListAction.OnTabSelected(1))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.favorites),
                            modifier = Modifier.padding(vertical = 12.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(Modifier.height(4.dp))
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) { pageIndex ->

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (pageIndex) {
                            0 -> {
                                if (state.isLoading) CircularProgressIndicator()
                                else {
                                    when {
                                        state.errorMsg != null -> {
                                            Text(
                                                text = state.errorMsg.asString(),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }

                                        state.searchResults.isEmpty() -> {
                                            Text(
                                                text = stringResource(Res.string.no_result),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }

                                        else -> {
                                            BookList(
                                                bookList = state.searchResults,
                                                onBookClick = {
                                                    onAction(BookListAction.OnBookClick(it))
                                                },
                                                modifier = Modifier.fillMaxSize(),
                                                scrollState = searchResultListState
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                if (state.favoriteBooks.isEmpty()){
                                    Text(
                                        text = stringResource(Res.string.no_result),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }else{
                                    BookList(
                                        bookList = state.favoriteBooks,
                                        onBookClick = {
                                            onAction(BookListAction.OnBookClick(it))
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                        scrollState = favoriteListState
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }
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