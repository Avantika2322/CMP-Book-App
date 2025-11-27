package com.cmp.bookapp.book.presentation.bookState

import com.cmp.bookapp.book.domain.model.Book

sealed interface BookListAction {
    data class OnSearchQueryChange(val query: String) : BookListAction
    data class OnBookClick(val book: Book) : BookListAction
    data class OnTabSelected(val index: Int) : BookListAction
}