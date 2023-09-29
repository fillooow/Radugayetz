package com.example

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.sticker
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.HideKeyboardReplyMarkup
import com.github.kotlintelegrambot.entities.ReplyMarkup
import com.github.kotlintelegrambot.entities.TelegramFile
import com.github.kotlintelegrambot.logging.LogLevel
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker
import java.io.File
import kotlin.random.Random

const val RANDOM_UNTIL = 7L
const val FRIENDS_STICKER_ID = "CAACAgIAAxkBAAEmZi9lFyuoaybgVIYcJYhemKCiR28V9wAC4wADwNw1NSPssrAvYB_CMAQ"
const val BRO_STICKER_ID = "CAACAgQAAxkBAAEmZhFlFylOMGWBdRrmKScmiuW9ROS0HQACpgADUCGkFvVZcNpNbZ6KMAQ"

val братья = mapOf(
    "ваня" to "@imelkozerov ",
    "маша" to "@mashhatt ",
    "филипп" to "@fillooow ",
    "денис" to "@java_agent ",
)

fun main() {
    val bot = bot {
        token = readApiKeyFromFile() ?: error("апи ключ отвалился")
        logLevel = LogLevel.All()
        dispatch {
            text {
                val randomAnswer = when (text.lowercase()) {
                    "да" -> "пизда"
                    "нет" -> "пидора ответ"
                    "пизда" -> "да"
                    "казахстан" -> "сверхдержавный край"
                    "грузия" -> "бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв бмв"
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

                val log = message.sticker?.fileId ?: message.text
                println(log)
                log?.let { writeLog(log) }

                val kal = when (message.text?.lowercase()) {
                    "кал" -> "белый"
                    "белый" -> "кал"
                    else -> null
                }
                kal?.let {
                    bot.sendMessage(
                        ChatId.fromId(message.chat.id), text = kal, replyToMessageId = message.messageId
                    )
                }

                val stickerId = message.sticker?.fileId
                if (stickerId != null) bot.sendMessage(
                    ChatId.fromId(message.chat.id), text = "стикер", replyToMessageId = message.messageId
                )

                val brats = listOf("брат", "братан", "братишка", "браток", "брателла")
                if (brats.any { brat -> message.text?.lowercase()?.split(" ")?.contains(brat) == true }) {
                    bot.sendSticker(
                        chatId = ChatId.fromId(message.chat.id),
                        sticker = BRO_STICKER_ID,
                        replyMarkup = HideKeyboardReplyMarkup(),
                    )
                }
                if (message.text?.lowercase() == "друзья") {
                    bot.sendSticker(
                        chatId = ChatId.fromId(message.chat.id),
                        sticker = FRIENDS_STICKER_ID,
                        replyMarkup = HideKeyboardReplyMarkup(),
                    )
                }
                if (message.sticker?.fileId == BRO_STICKER_ID) {
                    bot.sendSticker(
                        chatId = ChatId.fromId(message.chat.id),
                        sticker = BRO_STICKER_ID,
                        replyMarkup = HideKeyboardReplyMarkup(),
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

fun writeLog(text: String) {
    val fileName = "logs"
    val file = File(fileName)

    try {
        file.appendText("$text\n")
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}