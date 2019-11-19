package com.fiqih.event.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.fiqih.event.contract.DocumentContract
import com.fiqih.event.model.Document
import com.fiqih.event.model.itemDocument
import com.fiqih.event.presenter.APIRepositoryImplement
import com.fiqih.event.presenter.DocumentPresenter
import com.fiqih.event.rest.APIService
import com.fiqih.event.util.SessionManager
import com.fiqih.event.view.adapter.DocAdapter
import kotlinx.android.synthetic.main.activity_document.*
import android.net.Uri


class DocumentActivity: AppCompatActivity(), DocumentContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.fiqih.event.R.layout.activity_document)

        im_back.setOnClickListener {
            finish()
        }
        doRequest()
    }

    private lateinit var documentPresenter: DocumentPresenter
    private fun doRequest(){
        documentPresenter = DocumentPresenter(this@DocumentActivity, APIRepositoryImplement(
            APIService.Api())
        )
        documentPresenter.getDocument(SessionManager.getInstance(this).apptoken.getString("apptoken", "default_app_token"))
    }

    override fun listDocument(doc: Document) {
        rv_doc.apply {
            layoutManager = LinearLayoutManager(this@DocumentActivity)
            adapter = DocAdapter(doc.itemDocument, {partItem: itemDocument -> partItemClicked(partItem)})
        }
    }

    private fun partItemClicked(partItem: itemDocument) {
//        Toast.makeText(this, partItem.event_file, Toast.LENGTH_SHORT).show()
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(partItem.event_file))
        startActivity(browserIntent)
    }
}