package com.fiqih.event.contract

import com.fiqih.event.model.Document

interface DocumentContract{

    interface View{
        fun listDocument(doc: Document)
    }

    interface Presenter{
        fun getDocument(apptoken:String)
        fun destroyFetch()
    }
}