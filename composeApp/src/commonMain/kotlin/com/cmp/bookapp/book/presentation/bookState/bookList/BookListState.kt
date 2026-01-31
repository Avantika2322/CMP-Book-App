package com.cmp.bookapp.book.presentation.bookState.bookList

import com.cmp.bookapp.book.domain.model.Book
import com.cmp.bookapp.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
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
