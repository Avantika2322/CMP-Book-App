package com.cmp.bookapp.book.presentation.bookState

import com.cmp.bookapp.book.domain.model.Book
import com.cmp.bookapp.core.presentation.UiText

data class BookListState(
    val searchQuery : String = "",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMsg: UiText ? = null
)
