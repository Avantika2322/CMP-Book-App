package com.cmp.bookapp.book.domain.repository

import com.cmp.bookapp.book.domain.model.Book
import com.cmp.bookapp.core.domain.DataError
import com.cmp.bookapp.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}