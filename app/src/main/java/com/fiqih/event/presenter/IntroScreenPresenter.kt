package com.fiqih.event.presenter

import android.annotation.TargetApi
import android.os.Build
import android.support.annotation.RequiresApi
import com.fiqih.event.contract.IntroScreenContract
import com.fiqih.event.model.ScreenItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class IntroScreenPresenter(private val view: IntroScreenContract.View,
                           private val apiRepositoryImplement: APIRepositoryImplement): IntroScreenContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getIntroScreen() {
        compositeDisposable.add(apiRepositoryImplement.getIntroScreen().observeOn(AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<ScreenItem>(){

            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onNext(t: ScreenItem?) {
                view.listIntro(t!!)
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable?) {
//                Log.i("errordatanya : ", t!!.toString())
//                view.listIntro(Collections.emptyList())
            }

        }))
    }

    override fun destroyFetch() {
        compositeDisposable.dispose()
    }
}