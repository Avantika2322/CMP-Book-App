package com.cmp.bookapp.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.cmp.bookapp.app.navigation.Routes
import com.cmp.bookapp.book.presentation.bookState.bookDetail.BookDetailAction
import com.cmp.bookapp.book.presentation.ui.bookDetail.BookDetailScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.cmp.bookapp.book.presentation.viewmodel.BookListViewModel
import com.cmp.bookapp.book.presentation.ui.bookList.BookListScreenRoot
import com.cmp.bookapp.book.presentation.viewmodel.BookDetailViewModel
import com.cmp.bookapp.book.presentation.viewmodel.SelectedBookViewModel
import io.ktor.http.parametersOf
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

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
                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                    /*
                    *  reset the selectedBook whenever page will appear first time
                    */

                    LaunchedEffect(true) {
                        selectedBookViewModel.onSelectBook(null)
                    }

                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = { book ->
                            selectedBookViewModel.onSelectBook(book)
                            navController.navigate(Routes.BookDetail(book.id))
                        },
                    )
                }

                composable<Routes.BookDetail> { //entry ->
                    //val args = entry.toRoute<Routes.BookDetail>()

                    /*
                    * selectedBookViewModel is sharedViewModel i.e shared on both pages (BookList and BookDetail)
                    * */

                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()
                    val bookId = selectedBook?.id

                    bookId?.let { id ->

                        val bookDetailViewModel: BookDetailViewModel = koinViewModel(
                            key = id,
                            parameters = { parametersOf(id) }
                        )

                        LaunchedEffect(selectedBook) {
                            selectedBook?.let { book->
                                bookDetailViewModel.onAction(
                                    BookDetailAction.OnSelectedBookChange(
                                        book
                                    )
                                )
                            }
                        }

                        BookDetailScreenRoot(
                            viewModel = bookDetailViewModel,
                            onBackClick = { navController.navigateUp() }
                        )
                    }
//                    val bookDetailViewModel =  koinViewModel(
//                        parameters = { parametersOf(selectedBook?.id) }
//                    )
//
//                    LaunchedEffect(selectedBook){
//                        selectedBook?.let {
//                            bookDetailViewModel.onAction(BookDetailAction.OnSelectedBookChange(it))
//                        }
//                    }
//
//                    BookDetailScreenRoot(
//                        viewModel = bookDetailViewModel,
//                        onBackClick = {
//                            navController.navigateUp()
//                        }
//                    )
                }
            }
        }
    }
}


@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return koinViewModel(viewModelStoreOwner = parentEntry)
}