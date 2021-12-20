package com.shahad.app.chat.data

import android.util.Log
import com.google.firebase.firestore.ktx.*
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class ChatFireBase @Inject constructor(){
    private val db = Firebase.firestore
    private val messageReference = db.collection("messages")

    fun add(message: Message){
        messageReference.add(message).addOnSuccessListener {
            Log.i("CHAT_LOG","Success Add")
        }.addOnFailureListener {
            Log.i("CHAT_LOG","fail Add")
        }
    }

    fun getNewMessage(onGetMessages: (List<Message>) -> Unit){
        messageReference.addSnapshotListener { value, _ ->

            value?.let { snapShot ->
                snapShot
                    .toObjects<Message>()
                    .sortedBy { it.date }
                    .also {
                        onGetMessages(it)
                    }
            }
        }
    }

}