package com.fiqih.event.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import com.fiqih.event.R
import java.lang.Exception

class SplashActivity:AppCompatActivity(){

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
}