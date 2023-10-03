package com.example.bot_send

import com.example.utils.Defaults.BRO_STICKER_ID
import com.example.utils.Defaults.FRIENDS_STICKER_ID
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.HideKeyboardReplyMarkup

object BotSend {

    fun Bot.sendBroSticker(chatId: Long) = sendSticker(
        chatId = ChatId.fromId(chatId),
        sticker = BRO_STICKER_ID,
        replyMarkup = HideKeyboardReplyMarkup(),
    )

    fun Bot.sendFriendsSticker(chatId: Long) = sendSticker(
        chatId = ChatId.fromId(chatId),
        sticker = FRIENDS_STICKER_ID,
        replyMarkup = HideKeyboardReplyMarkup(),
    )
}