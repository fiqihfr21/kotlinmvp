package com.fiqih.event.presenter

import com.fiqih.event.contract.HomeBannerContract
import com.fiqih.event.model.Banner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class HomeBannerPresenter(private val view: HomeBannerContract.View,
                          private val apiRepositoryImplement: APIRepositoryImplement):HomeBannerContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getBanner(apptoken:String) {

        compositeDisposable.add(apiRepositoryImplement.getBanner(apptoken).observeOn(AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<Banner>(){
            override fun onComplete() {

            }

            override fun onNext(t: Banner?) {
                view.listProfile(t!!)
            }


            override fun onError(t: Throwable?) {

            }

        }))
    }

    override fun destroyFetch() {
        compositeDisposable.dispose()
    }

}