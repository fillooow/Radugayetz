package com.example.utils

object LogUtils {

    fun writeMessageLog(messageText: String?) {
        println(messageText)
        messageText?.let { writeLog(messageText) }
    }
}