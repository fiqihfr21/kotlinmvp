package com.fiqih.event.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.fiqih.event.R
import com.fiqih.event.contract.LoginContract
import com.fiqih.event.contract.SplaschScreenContract
import com.fiqih.event.model.AppKey
import com.fiqih.event.presenter.APIRepositoryImplement
import com.fiqih.event.presenter.LoginPresenter
import com.fiqih.event.presenter.SplashScreenPresenter
import com.fiqih.event.rest.APIService
import com.fiqih.event.util.SessionManager
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception

class SplashActivity:AppCompatActivity(), SplaschScreenContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        val background = object : Thread(){
            override fun run() {
                try {
                    Thread.sleep(5000)
                    val intent = Intent(baseContext, IntroActivity::class.java)
                    startActivity(intent)
                    finish()
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }

        background.start()
    }

    override fun onStart() {
        super.onStart()
        doRequest()
    }

    private lateinit var presenter : SplashScreenPresenter

    private fun doRequest(){
        presenter = SplashScreenPresenter(this, APIRepositoryImplement(APIService.Api()))
        presenter.getToken("Tra-1007", "8448")
    }

    override fun logRegResponse(logRegResponse: AppKey) {
        SessionManager.getInstance(this).saveAppToken(logRegResponse.Data)
//        Log.i("tes_app_token", SessionManager.getInstance(this).apptoken.getString("apptoken", "default_app_token"))
    }
}