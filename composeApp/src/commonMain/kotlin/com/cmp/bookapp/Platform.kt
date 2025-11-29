package com.cmp.bookapp

expect class Platform() {
    val name: String
}

expect fun getPlatform(): Platform
