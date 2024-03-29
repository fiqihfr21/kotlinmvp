package com.fiqih.event.presenter

import android.annotation.TargetApi
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import com.fiqih.event.contract.IntroScreenContract
import com.fiqih.event.model.ScreenItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class IntroScreenPresenter(private val view: IntroScreenContract.View,
                           private val apiRepositoryImplement: APIRepositoryImplement): IntroScreenContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getIntroScreen(apptoken : String) {
        compositeDisposable.add(apiRepositoryImplement.getIntroScreen(apptoken).observeOn(AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<ScreenItem>(){

            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onNext(t: ScreenItem?) {
                view.listIntro(t!!)
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable?) {

            }

        }))
    }

    override fun destroyFetch() {
        compositeDisposable.dispose()
    }
}