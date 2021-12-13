package com.shahad.app.chat.ui

import android.view.ViewGroup
import com.shahad.app.chat.R

class MessageRecycleAdapter(items: List<MessageItem>, listener: MessageInteractionListener):
    BaseRecyclerAdapter<MessageItem>(items, listener) {
    override var layoutId: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = getLayout(viewType)
        return super.onCreateViewHolder(parent, viewType)
    }

    private fun getLayout(viewType: Int): Int {
        return if (viewType== TYPE_RECEIVER){
            R.layout.message_recervier_item
        }else{
            R.layout.message_sender_item
        }
    }
    override fun getItemViewType(position: Int): Int =
        when (getItems()[position]) {
            is MessageItem.MessageReceiver -> TYPE_RECEIVER
            is MessageItem.MessageSender -> TYPE_SENDER
        }

    companion object {
        const val TYPE_SENDER = 1
        const val TYPE_RECEIVER = 2

    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<MessageItem>
    ): Boolean {
        return  getItems()[oldItemPosition].message.date ==newItems[newItemPosition].message.date
    }
}

interface MessageInteractionListener: BaseInteractionListener
