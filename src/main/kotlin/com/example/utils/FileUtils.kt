package com.example.utils

import java.io.File
import java.io.RandomAccessFile

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

fun readLastNLinesFromFile(n: Int): List<String> {
    val lines = mutableListOf<String>()

    RandomAccessFile("logs", "r").use { file ->
        val fileLength = file.length()
        val blockSize = 4096 // Размер блока для считывания (можно настроить)
        var currentPosition = fileLength - blockSize
        var remainingLines = n.coerceAtMost(500)

        while (currentPosition >= 0 && remainingLines > 0) {
            file.seek(currentPosition)
            val buffer = ByteArray(blockSize.coerceAtMost((fileLength - currentPosition).toInt()))
            file.read(buffer)

            // Разбиваем считанный буфер на строки
            val bufferString = String(buffer)
            val reversedLines = bufferString.split("\n").reversed()

            // Добавляем строки в обратном порядке в список lines
            for (line in reversedLines) {
                if (remainingLines > 0) {
                    lines.add(line)
                    remainingLines--
                } else {
                    break
                }
            }

            currentPosition -= blockSize
        }
    }

    return lines.reversed()
}

fun readLastLines() {
    val lastNLines = readLastNLinesFromFile(100)

    // Вывести последние 100 строк
    for (line in lastNLines) {
        println(line)
    }
}