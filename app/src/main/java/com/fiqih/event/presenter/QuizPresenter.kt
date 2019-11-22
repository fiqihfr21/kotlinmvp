package com.fiqih.event.presenter

import com.fiqih.event.contract.QuizContract
import com.fiqih.event.model.Galery
import com.fiqih.event.model.Quiz
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class QuizPresenter(private val view: QuizContract.View,
                     private val apiRepositoryImplement: APIRepositoryImplement): QuizContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getQuiz(apptoken: String) {

        compositeDisposable.add(apiRepositoryImplement.getQuiz(apptoken).observeOn(
            AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<Quiz>(){
            override fun onComplete() {

            }

            override fun onNext(t: Quiz?) {
                view.listQuiz(t!!)
            }


            override fun onError(t: Throwable?) {

            }

        }))
    }

    override fun destroyFetch() {
        compositeDisposable.dispose()

    }

}