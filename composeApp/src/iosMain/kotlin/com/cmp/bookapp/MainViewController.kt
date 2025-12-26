package com.cmp.bookapp

import androidx.compose.ui.window.ComposeUIViewController
import com.cmp.bookapp.app.App
import com.cmp.bookapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}