package com.fiqih.event.presenter

import com.fiqih.event.contract.EventContract
import com.fiqih.event.model.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class EventPresenter(private val view: EventContract.View,
                      private val apiRepositoryImplement: APIRepositoryImplement):
    EventContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getEvent(apptoken:String) {

        compositeDisposable.add(apiRepositoryImplement.getEvent(apptoken).observeOn(
            AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<Event>(){
            override fun onComplete() {

            }

            override fun onNext(t: Event?) {
                view.event(t!!)
            }


            override fun onError(t: Throwable?) {

            }

        }))
    }

    override fun destroyFetch() {
        compositeDisposable.dispose()
    }

}