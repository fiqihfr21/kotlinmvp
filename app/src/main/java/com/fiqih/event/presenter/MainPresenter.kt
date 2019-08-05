package com.fiqih.event.presenter

import com.fiqih.event.contract.MainContract
import com.fiqih.event.model.Profile
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class MainPresenter(private val view: MainContract.View,
                    private val apiRepositoryImplement: APIRepositoryImplement):MainContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getProfile() {

        view.showLoading()
        compositeDisposable.add(apiRepositoryImplement.getProfile().observeOn(AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<List<Profile>>(){
            override fun onComplete() {
                view.hideLoading()
            }

            override fun onNext(t: List<Profile>?) {
                view.listProfile(t!!)
            }

            override fun onError(t: Throwable?) {
                view.hideLoading()
                view.listProfile(Collections.emptyList())
            }

        }))
    }

    override fun destroyFetch() {
        compositeDisposable.dispose()
    }

}