package com.fiqih.event.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fiqih.event.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_galery.*

class DetailGaleryActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_galery)

        im_back.setOnClickListener {
            finish()
        }

        val bundle:Bundle? = intent.extras
        val img = bundle!!.getString("img")

        Picasso.get().load(img)
//            .placeholder(R.drawable.background_image_placeholder)
//            .error(R.drawable.background_image_placeholder)
            .into(im_detail_galery)
    }
}