package com.fiqih.event.presenter

import com.fiqih.event.contract.RegisterContract
import com.fiqih.event.model.UserID
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class RegisterPresenter(private val view: RegisterContract.View,
                     private val apiRepositoryImplement: APIRepositoryImplement): RegisterContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getUser(name:String, phone:String, email:String, password:String) {
        compositeDisposable.add(apiRepositoryImplement.register(name, phone, email,password).observeOn(AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<UserID>(){
            override fun onComplete() {
                view.hideLoading()
            }

            override fun onNext(t: UserID?) {
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