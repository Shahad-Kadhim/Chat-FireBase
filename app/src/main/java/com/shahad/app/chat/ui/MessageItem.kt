package com.shahad.app.chat.ui

import com.shahad.app.chat.data.Message


sealed class MessageItem(
    val message: Message,
    val type: MessageType
) {
    class MessageSender(message: Message): MessageItem(message, MessageType.SENDER)
    class MessageReceiver(message: Message): MessageItem(message, MessageType.RECEIVER)
}


