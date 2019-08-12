package com.fiqih.event.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.fiqih.event.R
import com.fiqih.event.contract.LoginContract
import com.fiqih.event.model.LogRegAPIResponse
import com.fiqih.event.presenter.APIRepositoryImplement
import com.fiqih.event.presenter.LoginPresenter
import com.fiqih.event.rest.APIService
import com.fiqih.event.util.SessionManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnlogin.setOnClickListener {
            val user = etemail.text
            val pass = etpassword.text

            if (validateLogin(user, pass)){
                doRequest()
            }
        }
    }

    private lateinit var presenter : LoginPresenter

    override fun onStart() {
        super.onStart()

        //SessionManager.getInstance(this).setFirstTimeLaunch(true)
        if(SessionManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun validateLogin(user: Editable, pass: Editable): Boolean {
        if (user == null || user.trim().isEmpty()){
            //Toast.makeText(this, "Masukan Email dan Password anda", Toast.LENGTH_SHORT).show()
            etemail.error = "Masukan Email Anda"
            etemail.requestFocus()
            return false
        }
        if (pass == null || pass.trim().isEmpty()){
            //Toast.makeText(this, "Masukan Email dan Password anda", Toast.LENGTH_SHORT).show()
            etpassword.error = "Masukan Password Anda"
            etpassword.requestFocus()
            return false
        }
        return true
    }

    private fun doRequest(){
        presenter = LoginPresenter(this, APIRepositoryImplement(APIService.create()))
        presenter.getUser(etemail.text.toString(), etpassword.text.toString())
    }

    override fun showLoading() {

    }

    override fun logRegResponse(logRegResponse: LogRegAPIResponse) {

        if(logRegResponse.error){
            Toast.makeText(this@LoginActivity, logRegResponse.error_msg, Toast.LENGTH_SHORT).show()
        }else{
            SessionManager.getInstance(applicationContext).saveUser(logRegResponse.user)
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
            Log.i("data user", ": $logRegResponse")
        }
    }

    override fun hideLoading() {

    }
}