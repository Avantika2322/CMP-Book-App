package com.cmp.bookapp.book.data.network

import com.cmp.bookapp.book.data.dto.SearchResDto
import com.cmp.bookapp.core.domain.DataError
import com.cmp.bookapp.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchResDto, DataError.Remote>
}