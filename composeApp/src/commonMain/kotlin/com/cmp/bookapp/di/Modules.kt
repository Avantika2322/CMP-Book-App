package com.cmp.bookapp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.cmp.bookapp.book.data.database.DatabaseFactory
import com.cmp.bookapp.book.data.database.FavoriteBookDatabase
import com.cmp.bookapp.book.data.network.KtorRemoteBookDataSource
import com.cmp.bookapp.book.data.network.RemoteBookDataSource
import com.cmp.bookapp.book.data.repository.BookRepositoryImpl
import com.cmp.bookapp.book.domain.repository.BookRepository
import com.cmp.bookapp.book.presentation.viewmodel.BookDetailViewModel
import com.cmp.bookapp.book.presentation.viewmodel.BookListViewModel
import com.cmp.bookapp.book.presentation.viewmodel.SelectedBookViewModel
import com.cmp.bookapp.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
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

    viewModel { params ->
        BookDetailViewModel(
            bookRepository = get(),
            bookId = params.get()
        )
    }
   // viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavoriteBookDatabase>().favoriteBookDao }

}