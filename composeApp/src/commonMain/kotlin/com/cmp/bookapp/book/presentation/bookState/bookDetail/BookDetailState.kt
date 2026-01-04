package com.cmp.bookapp.book.presentation.bookState.bookDetail

import com.cmp.bookapp.book.domain.model.Book

data class BookDetailState(
    val isLoading : Boolean = true,
    val isFavorite : Boolean = false,
    val book: Book? = null,

    )