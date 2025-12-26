package com.cmp.bookapp.book.data.repository

import com.cmp.bookapp.book.data.mappers.toBook
import com.cmp.bookapp.book.data.network.RemoteBookDataSource
import com.cmp.bookapp.book.domain.model.Book
import com.cmp.bookapp.book.domain.repository.BookRepository
import com.cmp.bookapp.core.domain.DataError
import com.cmp.bookapp.core.domain.Result
import com.cmp.bookapp.core.domain.map

class BookRepositoryImpl (
    private val remoteBookDataSource: RemoteBookDataSource
): BookRepository {

    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>{
        return remoteBookDataSource.searchBooks(
            query,
            resultLimit = null
        ).map { dto ->
            dto.results.map { it.toBook() }
        }
    }
}