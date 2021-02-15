package com.amk.stackoverflowreader.ui

import android.util.Log

object Logger {

    fun printError(tag: String, message: String) {
        Log.e(tag, message)
    }
}