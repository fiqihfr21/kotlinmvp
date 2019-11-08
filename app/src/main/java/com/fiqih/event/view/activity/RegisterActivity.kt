package com.fiqih.event.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.fiqih.event.R
import com.fiqih.event.contract.RegisterContract
import com.fiqih.event.model.UserID
import com.fiqih.event.presenter.APIRepositoryImplement
import com.fiqih.event.presenter.RegisterPresenter
import com.fiqih.event.rest.APIService
import com.fiqih.event.util.SessionManager
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegist.setOnClickListener{
            val user = et_username.text
            val phone = et_mobile_phone.text
            val eml = et_email.text
            val pass = et_password.text
            val kpass = et_confirm_password.text

            if(validateRegister(user,phone, eml, pass, kpass)){
                doRequest()
            }
        }

        btnLogin.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun validateRegister(user: Editable, phone:Editable, eml: Editable, pass: Editable, kpass: Editable): Boolean {
        if (user == null || user.trim().isEmpty()){
            et_username.error = "Masukan Username Anda"
            et_username.requestFocus()
            return false
        }
        if (phone == null || phone.trim().isEmpty()){
            et_mobile_phone.error = "Masukan Nomor Handphone Anda"
            et_mobile_phone.requestFocus()
            return false
        }
        if (eml == null || eml.trim().isEmpty()){
            et_email.error = "Masukan Email Anda"
            et_email.requestFocus()
            return false
        }
        if (pass == null || pass.trim().isEmpty()){
            et_password.error = "Masukan Password Anda"
            et_password.requestFocus()
            return false
        }
        if (kpass == null || kpass.trim().isEmpty()){
            et_confirm_password.error = "Masukan Konfirmasi Email Anda"
            et_confirm_password.requestFocus()
            return false
        }
        if(pass.trim() != kpass.trim()){
            et_confirm_password.error = "Password Harus Sama dengan Konfirmasi Password !"
            et_confirm_password.requestFocus()
            return false
        }
        return true
    }

    private lateinit var presenter : RegisterPresenter
    private fun doRequest(){
        presenter = RegisterPresenter(this, APIRepositoryImplement(APIService.Api()))
        presenter.getUser(et_username.text.toString(), et_mobile_phone.text.toString(), et_email.text.toString(), et_password.text.toString())
    }

    override fun showLoading() {

    }

    override fun logRegResponse(logRegResponse: UserID){
        Log.i("status_code: ", logRegResponse.StatusCode.toString())
        if(logRegResponse.StatusCode.toString() == "400"){
            Toast.makeText(this@RegisterActivity, logRegResponse.Error, Toast.LENGTH_SHORT).show()
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