package com.fiqih.event.presenter

import com.fiqih.event.contract.IntroScreenContract
import com.fiqih.event.model.Banner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class IntroScreenPresenter(private val view: IntroScreenContract.View,
                           private val apiRepositoryImplement: APIRepositoryImplement): IntroScreenContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getBanner(auth: String) {
        compositeDisposable.add(apiRepositoryImplement.getBanner(auth).observeOn(AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<List<Banner>>(){
            override fun onComplete() {

            }

            override fun onNext(t: List<Banner>?) {
                view.listIntro(t!!)
            }

            override fun onError(t: Throwable?) {

                view.listIntro(Collections.emptyList())
            }

        }))
    }

    override fun destroyFetch() {
        compositeDisposable.dispose()
    }
}