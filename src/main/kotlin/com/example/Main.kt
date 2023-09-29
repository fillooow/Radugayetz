package com.example

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import java.io.File
import kotlin.random.Random

const val RANDOM_UNTIL = 7L

val братья = mapOf(
    "ваня" to "@imelkozerov ",
    "маша" to "@mashhatt ",
    "филипп" to "@fillooow ",
    "денис" to "@java_agent ",
)

fun main() {
    val bot = bot {
        token = readApiKeyFromFile() ?: error("апи ключ отвалился")
        dispatch {
            text {
                val randomAnswer = when (text.lowercase()) {
                    "да" -> "пизда"
                    "нет" -> "пидора ответ"
                    "пизда" -> "да"
                    "казахстан" -> "сверхдержавный край"
                    "грузия" -> "бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв д"
                    else -> null
                }
                randomAnswer?.let {
                    val key = Random.nextLong(RANDOM_UNTIL)
                    if (key == 1L) bot.sendMessage(ChatId.fromId(message.chat.id), text = randomAnswer)
                }
                val брат = братья.keys.firstOrNull { key -> text.lowercase().contains(key) }
                if (брат != null) {
                    bot.sendMessage(
                        ChatId.fromId(message.chat.id),
                        text = "${text.lowercase().replace(брат, братья.getValue(брат))}",
                        replyToMessageId = message.messageId,
                    )
                }
            }
        }
    }
    bot.startPolling()
}

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