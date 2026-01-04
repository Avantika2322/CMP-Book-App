package com.cmp.bookapp.book.data.mappers

import com.cmp.bookapp.book.data.dto.SearchedBooksDto
import com.cmp.bookapp.book.domain.model.Book

fun SearchedBooksDto.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title,
        languages = languages ?: emptyList(),
        imageUrl = if(coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
        },
        authors = authorNames ?: emptyList(),
        description = null,
        firstPublishYear = publishYear.toString(),
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numPages = noOfPages,
        numEdition = numEdition ?: 0,
    )
}

/*
fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        authors = authors,
        firstPublishYear = firstPublishYear,
        ratingsAverage = averageRating,
        ratingsCount = ratingCount,
        numPagesMedian = numPages,
        numEdition = numEdition
    )
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        authors = authors,
        firstPublishYear = firstPublishYear,
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numPages = numPagesMedian,
        numEdition = numEdition
    )
}*/
