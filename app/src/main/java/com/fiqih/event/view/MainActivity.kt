package com.fiqih.event.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fiqih.event.R
import com.fiqih.event.contract.MainContract
import com.fiqih.event.model.Profile
import com.fiqih.event.presenter.APIRepositoryImplement
import com.fiqih.event.presenter.MainPresenter
import com.fiqih.event.rest.APIService

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doRequest()
    }

    private lateinit var presenter: MainPresenter

    private fun doRequest(){
        presenter =  MainPresenter(this, APIRepositoryImplement(APIService.create()))
        presenter.getProfile()

    }

    override fun showLoading() {

    }

    override fun listProfile(profile: List<Profile>) {
        Log.i("Main Activity : ", "Data profile : $profile")
    }

    override fun hideLoading() {

    }
}
