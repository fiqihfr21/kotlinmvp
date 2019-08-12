package com.fiqih.event.presenter

import android.provider.ContactsContract
import com.fiqih.event.contract.HomeBannerContract
import com.fiqih.event.model.Banner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class HomeBannerPresenter(private val view: HomeBannerContract.View,
                          private val apiRepositoryImplement: APIRepositoryImplement):HomeBannerContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getBanner(auth:String) {

        view.showLoading()
        compositeDisposable.add(apiRepositoryImplement.getBanner(auth).observeOn(AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<List<Banner>>(){
            override fun onComplete() {
                view.hideLoading()
            }

            override fun onNext(t: List<Banner>?) {
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