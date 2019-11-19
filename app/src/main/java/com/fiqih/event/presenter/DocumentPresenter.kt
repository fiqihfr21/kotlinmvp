package com.fiqih.event.presenter

import com.fiqih.event.contract.DocumentContract
import com.fiqih.event.model.Document
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class DocumentPresenter(private val view: DocumentContract.View,
                          private val apiRepositoryImplement: APIRepositoryImplement):
    DocumentContract.Presenter{

    private val compositeDisposable = CompositeDisposable()

    override fun getDocument(apptoken:String) {

        compositeDisposable.add(apiRepositoryImplement.getDocument(apptoken).observeOn(
            AndroidSchedulers.mainThread()).subscribeOn(
            Schedulers.io()).subscribeWith(object : ResourceSubscriber<Document>(){
            override fun onComplete() {

            }

            override fun onNext(t: Document?) {
                view.listDocument(t!!)
            }


            override fun onError(t: Throwable?) {

            }

        }))
    }

    override fun destroyFetch() {
        compositeDisposable.dispose()
    }

}