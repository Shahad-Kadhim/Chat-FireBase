package com.shahad.app.chat.data

import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val chatFireBase: ChatFireBase
){

     fun sentMessage(message: Message){
         chatFireBase.add(message)
     }

    fun getMessages(onGetMessages: (List<Message>) -> Unit) =
        chatFireBase.getNewMessage(onGetMessages)



}