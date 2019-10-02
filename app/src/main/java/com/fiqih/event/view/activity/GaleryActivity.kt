package com.fiqih.event.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.fiqih.event.R
import com.fiqih.event.model.Galery
import com.fiqih.event.view.adapter.GridGaleryAdapter
import kotlinx.android.synthetic.main.activity_galery.*

class GaleryActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galery)

        im_back.setOnClickListener {
            finish()
        }

        val listGalery = ArrayList<Galery>()
        listGalery.add(Galery("https://cdn.lynda.com/course/628695/628695-636440274728135072-16x9.jpg"))
        listGalery.add(Galery("https://cdn.lynda.com/course/628695/628695-636440274728135072-16x9.jpg"))
        listGalery.add(Galery("https://cdn.lynda.com/course/628695/628695-636440274728135072-16x9.jpg"))
        listGalery.add(Galery("https://cdn.lynda.com/course/628695/628695-636440274728135072-16x9.jpg"))
        listGalery.add(Galery("https://cdn.lynda.com/course/628695/628695-636440274728135072-16x9.jpg"))
        listGalery.add(Galery("https://cdn.lynda.com/course/628695/628695-636440274728135072-16x9.jpg"))
        listGalery.add(Galery("https://cdn.lynda.com/course/628695/628695-636440274728135072-16x9.jpg"))

        val myrv = findViewById<RecyclerView>(R.id.grid_galery)
        val myAdapter = GridGaleryAdapter(listGalery, { partItem: Galery -> partItemClicked(partItem) })
        myrv.layoutManager = GridLayoutManager(this, 3)
        myrv.adapter = myAdapter
    }

    private fun partItemClicked(partItem: Galery) {
        //Toast.makeText(this, partItem.image, Toast.LENGTH_SHORT).show()
        val img : String = partItem.image
        val intent = Intent(this, DetailGaleryActivity::class.java)
        intent.putExtra("img", img)
        startActivity(intent)
    }
}