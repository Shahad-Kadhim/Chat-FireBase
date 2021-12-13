package com.shahad.app.chat.ui

import androidx.lifecycle.*
import com.shahad.app.chat.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val chatRepository: ChatRepository
): ViewModel() {

    private val _messages= MutableLiveData<List<MessageItem>>()
    val messages: LiveData<List<MessageItem>> =_messages

    val content = MutableLiveData<String>()
    val userName = MutableLiveData<String>("shahad")

    init {
        getMessages()
    }

    fun onCLickSend(){
        sendMessage()
        clearContentField()
    }

    private fun clearContentField() {
        content.postValue("")
    }

    private fun sendMessage() {
        userName.value?.let{ userName ->
            content.value?.let{ content ->
                chatRepository
                    .sentMessage(
                        Message(userName,content)
                    )
            }
        }
    }

    private fun getMessages(){
        chatRepository.getMessages {

            _messages.postValue(
                it.map{messages ->
                    toMessageItem(messages)
                }
            )
        }
    }
    private fun toMessageItem(message: Message): MessageItem {
        return if(message.userName==userName.value){
            MessageItem.MessageSender(message)
        }
        else{
            MessageItem.MessageReceiver(message)
        }
    }

}