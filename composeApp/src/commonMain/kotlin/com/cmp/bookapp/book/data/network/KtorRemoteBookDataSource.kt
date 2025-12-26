package com.cmp.bookapp.book.data.network

import com.cmp.bookapp.book.data.dto.SearchResDto
import com.cmp.bookapp.book.domain.model.Book
import com.cmp.bookapp.core.data.safeCall
import com.cmp.bookapp.core.domain.DataError
import com.cmp.bookapp.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://openlibrary.org/"

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
) : RemoteBookDataSource {

    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchResDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/search.json"
            ) {
                parameter("q", query)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter(
                    "fields",
                    "key, title, language,cover_i,author_key,author_name,cover_edition_key,first_publish_year,ratings_average,ratings_count,no_of_pages_median,edition_count"
                )
            }
        }
    }
}