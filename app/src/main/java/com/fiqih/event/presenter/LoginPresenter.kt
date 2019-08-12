package com.fiqih.event.presenter

import com.fiqih.event.contract.LoginContract
import com.fiqih.event.model.LogRegAPIResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class LoginPresenter(private val view: LoginContract.View,
                     private val apiRepositoryImplement: APIRepositoryImplement):LoginContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getUser(email:String, password:String) {
        compositeDisposable.add(apiRepositoryImplement.login(email,password).observeOn(AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<LogRegAPIResponse>(){
            override fun onComplete() {
                view.hideLoading()
            }

            override fun onNext(t: LogRegAPIResponse?) {
                view.logRegResponse(t!!)
            }

            override fun onError(t: Throwable?) {
                view.hideLoading()
            }

        }))
    }

    override fun destroyFetch() {
        compositeDisposable.dispose()
    }

}