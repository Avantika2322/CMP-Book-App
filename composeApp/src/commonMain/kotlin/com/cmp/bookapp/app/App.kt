package com.cmp.bookapp.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.cmp.bookapp.app.navigation.Routes
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.cmp.bookapp.book.presentation.bookState.BookListViewModel
import com.cmp.bookapp.book.presentation.ui.BookListScreenRoot
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.BookGraph,
        ) {
            navigation<Routes.BookGraph>(startDestination = Routes.BookList) {
                composable<Routes.BookList> {
                    val viewModel = koinViewModel<BookListViewModel>()
                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = { book->
                            navController.navigate(Routes.BookDetail(book.id))
                        },
                    )
                }

                composable<Routes.BookDetail>{ entry->
                    val args = entry.toRoute<Routes.BookDetail>()
                }
            }
        }
    }
}