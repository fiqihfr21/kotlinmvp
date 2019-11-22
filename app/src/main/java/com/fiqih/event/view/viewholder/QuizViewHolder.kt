package com.fiqih.event.view.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fiqih.event.R
import com.fiqih.event.model.itemQuiz
import kotlinx.android.synthetic.main.item_quiz.view.*

class QuizViewHolder (inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_quiz, parent, false)){

        private val quizName = itemView.tv_quiz_name

        fun bind(quiz: itemQuiz, clickListener: (itemQuiz) -> Unit){

            quizName.setText(quiz.name)

            itemView.setOnClickListener { clickListener(quiz)}
        }

}