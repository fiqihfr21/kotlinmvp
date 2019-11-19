package com.fiqih.event.presenter

import com.fiqih.event.contract.DocumentContract
import com.fiqih.event.contract.GaleryContract
import com.fiqih.event.model.Document
import com.fiqih.event.model.Galery
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class GaleryPresenter(private val view: GaleryContract.View,
                        private val apiRepositoryImplement: APIRepositoryImplement):
    GaleryContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getGalery(apptoken:String) {

        compositeDisposable.add(apiRepositoryImplement.getGalery(apptoken).observeOn(
            AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<Galery>(){
            override fun onComplete() {

            }

            override fun onNext(t: Galery?) {
                view.listGalery(t!!)
            }


            override fun onError(t: Throwable?) {

            }

        }))
    }

    override fun destroyFetch() {
        compositeDisposable.dispose()
    }

}