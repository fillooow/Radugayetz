package com.example.utils

import java.io.File

fun readApiKeyFromFile(): String? {
    val fileName = "secret"
    val file = File(fileName)

    return try {
        val apiKey = file.readText()
        apiKey.trim()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun writeLog(text: String) {
    val fileName = "logs"
    val file = File(fileName)

    try {
        file.appendText("$text\n")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}