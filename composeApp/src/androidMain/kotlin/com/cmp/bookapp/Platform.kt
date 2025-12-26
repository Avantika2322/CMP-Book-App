package com.cmp.bookapp

import android.os.Build

actual class Platform actual constructor() {
    actual val name: String = "Android ${Build.VERSION.RELEASE}"
}

actual fun getPlatform(): Platform = Platform()
