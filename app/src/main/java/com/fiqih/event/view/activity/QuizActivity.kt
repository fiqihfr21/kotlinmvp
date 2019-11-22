package com.fiqih.event.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.fiqih.event.R
import com.fiqih.event.contract.QuizContract
import com.fiqih.event.model.Quiz
import com.fiqih.event.model.itemGalery
import com.fiqih.event.model.itemQuiz
import com.fiqih.event.presenter.APIRepositoryImplement
import com.fiqih.event.presenter.GaleryPresenter
import com.fiqih.event.presenter.QuizPresenter
import com.fiqih.event.rest.APIService
import com.fiqih.event.util.SessionManager
import com.fiqih.event.view.adapter.GridGaleryAdapter
import com.fiqih.event.view.adapter.QuizAdapter
import kotlinx.android.synthetic.main.activity_galery.*

class QuizActivity: AppCompatActivity(), QuizContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        im_back.setOnClickListener {
            finish()
        }

        doRequest()
    }

    private lateinit var quizPresenter: QuizPresenter

    private fun doRequest(){
        quizPresenter = QuizPresenter(this@QuizActivity, APIRepositoryImplement(
            APIService.Api())
        )
        quizPresenter.getQuiz(SessionManager.getInstance(this).apptoken.getString("apptoken", "default_app_token"))
    }

    override fun listQuiz(quiz: Quiz) {
        val myrv = findViewById<RecyclerView>(R.id.rv_list_quiz)
        val myAdapter = QuizAdapter(quiz.itemQuiz, { partItem: itemQuiz -> partItemClicked(partItem) })
        myrv.layoutManager = LinearLayoutManager(this)
        myrv.adapter = myAdapter

    }

    private fun partItemClicked(partItem: itemQuiz) {
        //Toast.makeText(this, partItem.image, Toast.LENGTH_SHORT).show()
        val quizName : String = partItem.name
        val intent = Intent(this, QuizQuestionActivity::class.java)
        intent.putExtra("quizName", quizName)
        startActivity(intent)
    }
}