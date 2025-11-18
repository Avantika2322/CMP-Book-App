package com.cmp.bookapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform