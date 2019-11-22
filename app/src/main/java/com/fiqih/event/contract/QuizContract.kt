package com.fiqih.event.contract

import com.fiqih.event.model.Quiz

interface QuizContract{

    interface View{
        fun listQuiz(quiz: Quiz)
    }

    interface Presenter{
        fun getQuiz(apptoken:String)
        fun destroyFetch()
    }
}