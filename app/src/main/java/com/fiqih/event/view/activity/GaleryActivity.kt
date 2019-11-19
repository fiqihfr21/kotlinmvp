package com.fiqih.event.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.fiqih.event.R
import com.fiqih.event.contract.GaleryContract
import com.fiqih.event.model.Galery
import com.fiqih.event.model.itemGalery
import com.fiqih.event.presenter.APIRepositoryImplement
import com.fiqih.event.presenter.DocumentPresenter
import com.fiqih.event.presenter.GaleryPresenter
import com.fiqih.event.rest.APIService
import com.fiqih.event.util.SessionManager
import com.fiqih.event.view.adapter.GridGaleryAdapter
import kotlinx.android.synthetic.main.activity_galery.*

class GaleryActivity: AppCompatActivity(), GaleryContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galery)

        im_back.setOnClickListener {
            finish()
        }

        doRequest()
    }

    private lateinit var galeryPresenter: GaleryPresenter
    private fun doRequest(){
        galeryPresenter = GaleryPresenter(this@GaleryActivity, APIRepositoryImplement(
            APIService.Api())
        )
        galeryPresenter.getGalery(SessionManager.getInstance(this).apptoken.getString("apptoken", "default_app_token"))
    }

    override fun listGalery(galery: Galery) {
        val myrv = findViewById<RecyclerView>(R.id.grid_galery)
        val myAdapter = GridGaleryAdapter(galery.itemGalery, { partItem: itemGalery -> partItemClicked(partItem) })
        myrv.layoutManager = GridLayoutManager(this, 3)
        myrv.adapter = myAdapter
    }

    private fun partItemClicked(partItem: itemGalery) {
        //Toast.makeText(this, partItem.image, Toast.LENGTH_SHORT).show()
        val img : String = partItem.galery_image
        val intent = Intent(this, DetailGaleryActivity::class.java)
        intent.putExtra("img", img)
        startActivity(intent)
    }
}