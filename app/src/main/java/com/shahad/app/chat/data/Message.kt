package com.shahad.app.chat.data

import java.util.*

data class Message(
    val userName: String?=null,
    val content: String?=null,
    val date: Long =Date().time
){
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userName" to userName,
            "content" to content,
            "date" to date,
        )
    }
}