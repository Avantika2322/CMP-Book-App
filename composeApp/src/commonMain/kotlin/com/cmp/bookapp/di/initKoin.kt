package com.cmp.bookapp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        printLogger()
        config?.invoke(this)
        modules(sharedModule, platformModule)
    }
}