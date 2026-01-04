package com.cmp.bookapp.book.presentation.bookState.bookDetail

import com.cmp.bookapp.book.domain.model.Book

sealed interface BookDetailAction {
    data object OnBackClick: BookDetailAction
    data object OnFavoriteClick: BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}