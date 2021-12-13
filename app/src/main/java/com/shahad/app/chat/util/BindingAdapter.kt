package com.shahad.app.chat.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shahad.app.chat.ui.BaseRecyclerAdapter
import java.util.*


@BindingAdapter(value = ["app:items"])
fun <T>setItemsList(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseRecyclerAdapter<T>?)?.let {
        items?.let { list ->
            it.setItems(list)
        }
    }
    items?.size?.let {
        view.layoutManager?.scrollToPosition(it-1)
    }
}


@BindingAdapter(value = ["app:setTime"])
fun setTime(view: TextView, dateTime: Long?){
    dateTime?.let { dateTime ->
        Calendar.getInstance().apply {
            time = Date(dateTime)
            view.text ="${get(Calendar.HOUR)}:${get(Calendar.MINUTE)}"
        }

    }
}