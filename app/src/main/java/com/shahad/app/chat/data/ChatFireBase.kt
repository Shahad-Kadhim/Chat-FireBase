package com.shahad.app.chat.data

import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.*
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class ChatFireBase @Inject constructor(){
    private val db = Firebase.database
    private val messageReference = db.getReference("messages")

    fun add(message: Message){
        messageReference.push().setValue(message).addOnSuccessListener {
            Log.i("CHAT_LOG","Success Add")
        }.addOnFailureListener {
            Log.i("CHAT_LOG","fail Add")

        }
    }

    fun getNewMessage(onGetMessages: (List<Message>) -> Unit){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.map { it.getValue<Message>() }.let {
                    onGetMessages(it.filterNotNull())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        messageReference.addValueEventListener(postListener)
    }

}