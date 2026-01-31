package com.cmp.bookapp.book.domain.repository

import com.cmp.bookapp.book.domain.model.Book
import com.cmp.bookapp.core.domain.DataError
import com.cmp.bookapp.core.domain.EmptyResult
import com.cmp.bookapp.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>

    fun getFavoriteBooks(): Flow<List<Book>>
    fun isBookFavorite(id: String): Flow<Boolean>
    suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorites(id: String)
}