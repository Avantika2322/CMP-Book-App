package com.cmp.bookapp.book.presentation.bookState

import com.cmp.bookapp.book.domain.model.Book
import com.cmp.bookapp.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "",
    val searchResults: List<Book> = books,
    val favoriteBooks: List<Book> = books,
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMsg: UiText? = null
)

private val books = (1..10).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://test.com",
        authors = listOf("Author"),
        description = "fnfd  fmdmdfmf",
        languages = listOf("English"),
        firstPublishYear = null,
        averageRating = 4.56565,
        ratingCount = 5,
        numPages = null,
        numEdition = 8
    )
}
