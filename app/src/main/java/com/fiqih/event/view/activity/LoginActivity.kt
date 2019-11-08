package com.fiqih.event.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.fiqih.event.R
import com.fiqih.event.contract.LoginContract
import com.fiqih.event.model.UserID
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
            Log.i("btnlogin", "ok")
            val user = etemail.text
            val pass = etpassword.text

            if (validateLogin(user, pass) == true){
                doRequest()
            }
        }

        btnRegist.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private lateinit var presenter : LoginPresenter

    override fun onStart() {
        super.onStart()

        if(SessionManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun validateLogin(user: Editable, pass: Editable): Boolean {
        if (user == null || user.trim().isEmpty()){
            etemail.error = "Masukan Email Anda"
            etemail.requestFocus()
            return false
        }
        if (pass == null || pass.trim().isEmpty()){
            etpassword.error = "Masukan Password Anda"
            etpassword.requestFocus()
            return false
        }
        return true
    }

    private fun doRequest(){
        presenter = LoginPresenter(this, APIRepositoryImplement(APIService.Api()))
        presenter.getUser(etemail.text.toString(), etpassword.text.toString())
    }

    override fun showLoading() {

    }

    override fun logRegResponse(logRegResponse: UserID) {
        Log.i("status_code: ", logRegResponse.StatusCode.toString())
        if(logRegResponse.StatusCode.toString() == "400"){
            Toast.makeText(this@LoginActivity, logRegResponse.Error, Toast.LENGTH_SHORT).show()
        }else{
            SessionManager.getInstance(applicationContext).saveUser(logRegResponse)
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    override fun hideLoading() {

    }
}