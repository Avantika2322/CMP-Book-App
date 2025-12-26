package com.cmp.bookapp.di

import com.cmp.bookapp.Platform
import com.cmp.bookapp.book.data.network.KtorRemoteBookDataSource
import com.cmp.bookapp.book.data.network.RemoteBookDataSource
import com.cmp.bookapp.book.data.repository.BookRepositoryImpl
import com.cmp.bookapp.book.domain.repository.BookRepository
import com.cmp.bookapp.book.presentation.bookState.BookListViewModel
import com.cmp.bookapp.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val  platformModule: Module

val sharedModule = module {
    single {
        HttpClientFactory.create(get())
    }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::BookRepositoryImpl).bind<BookRepository>()
    viewModelOf(::BookListViewModel)
}