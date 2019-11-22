package com.fiqih.event.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fiqih.event.model.itemQuiz
import com.fiqih.event.view.viewholder.QuizViewHolder

class QuizAdapter (private val list: List<itemQuiz>, val clickListener : (itemQuiz)->Unit) : RecyclerView.Adapter<QuizViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): QuizViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        return QuizViewHolder(inflater, p0)    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: QuizViewHolder, p1: Int) {
        val mQuiz = list[p1]
        p0.bind(mQuiz, clickListener)
    }

}
