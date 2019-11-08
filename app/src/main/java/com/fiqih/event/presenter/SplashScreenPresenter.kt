package com.fiqih.event.presenter

import android.annotation.TargetApi
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import com.fiqih.event.contract.IntroScreenContract
import com.fiqih.event.contract.SplaschScreenContract
import com.fiqih.event.model.AppKey
import com.fiqih.event.model.ScreenItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber


class SplashScreenPresenter(private val view: SplaschScreenContract.View,
                           private val apiRepositoryImplement: APIRepositoryImplement): SplaschScreenContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getToken(apptoken:String, securitykey:String) {
        compositeDisposable.add(apiRepositoryImplement.getAppToken(apptoken, securitykey).observeOn(AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<AppKey>(){

            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(Build.VERSION_CODES.N)

            override fun onNext(t: AppKey?) {
                view.logRegResponse(t!!)
                Log.i("actkey getoken: ", t.toString())
            }

            override fun onComplete() {

            }

            override fun onError(t: Throwable?) {

            }

        }))
    }

}