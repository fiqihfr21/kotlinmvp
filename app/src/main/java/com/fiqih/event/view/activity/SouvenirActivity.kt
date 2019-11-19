package com.fiqih.event.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fiqih.event.R
import kotlinx.android.synthetic.main.activity_souvenir.*

class SouvenirActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_souvenir)

        im_back.setOnClickListener {
            finish()
        }
    }
}