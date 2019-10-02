package com.fiqih.event.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.fiqih.event.R
import com.fiqih.event.model.Document
import com.fiqih.event.model.Galery
import com.fiqih.event.view.adapter.DocAdapter
import kotlinx.android.synthetic.main.activity_document.*

class DocumentActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)

        im_back.setOnClickListener {
            finish()
        }

        val listDoc = ArrayList<Document>()
        listDoc.add(Document("Company Profile", "154kb"))
        listDoc.add(Document("Company Profile", "154kb"))
        listDoc.add(Document("Company Profile", "154kb"))
        listDoc.add(Document("Company Profile", "154kb"))
        listDoc.add(Document("Company Profile", "154kb"))
        listDoc.add(Document("Company Profile", "154kb"))
        listDoc.add(Document("Company Profile", "154kb"))
        listDoc.add(Document("Company Profile", "154kb"))
        listDoc.add(Document("Company Profile", "154kb"))
        listDoc.add(Document("Company Profile", "154kb"))
        listDoc.add(Document("Company Profile", "154kb"))
        listDoc.add(Document("Company Profile", "154kb"))

        rv_doc.apply {
            layoutManager = LinearLayoutManager(this@DocumentActivity)
            adapter = DocAdapter(listDoc, {partItem: Document -> partItemClicked(partItem)})
        }
    }

    private fun partItemClicked(partItem: Document) {
        Toast.makeText(this, partItem.filename, Toast.LENGTH_SHORT).show()
//        val img : String = partItem.image
//        val intent = Intent(this, DetailGaleryActivity::class.java)
//        intent.putExtra("img", img)
//        startActivity(intent)
    }
}