package com.fiqih.event.util

import android.content.Context
import android.content.SharedPreferences
import com.fiqih.event.model.UserID

class SessionManager private constructor(private val context: Context){

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: SharedPreferences
        get() {
            val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            sharedPreferences.getInt("id", 1)
            sharedPreferences.getString("token", null)
            sharedPreferences.getString("img_profile", "image/profile/default.png")
            return sharedPreferences
//            return User(
//                sharedPreferences.getInt("id", 1),
//                sharedPreferences.getString("token", null),
//                sharedPreferences.getInt("status_code", 200),
//                sharedPreferences.getString("error", null)
//            )
        }

    fun saveUser(user: UserID?) {

        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("id", user!!.user.id)
        editor.putString("token", user!!.Token)
        editor.putString("img_profile", user!!.user.user_image)
        editor.commit()

    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor.commit()
    }

    fun saveAppToken(apptoken : String){
        val sharedPreferences = context.getSharedPreferences(SHARE_PREF_APP_TOKEN, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("apptoken", apptoken)
        editor.commit()
    }

    val apptoken: SharedPreferences
        get() {
            val sharedPreferences = context.getSharedPreferences(SHARE_PREF_APP_TOKEN, Context.MODE_PRIVATE)
            sharedPreferences.getString("apptoken", "default_app_token")
            return sharedPreferences
        }

    val isFirstTimeLaunch: Boolean
        get() {
            val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, false)
        }

    fun clear() {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {

        private val PREF_NAME = "introslider"

        private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

        private val SHARED_PREF_NAME = "MY_SHARED_PREFERENCE"

        private val SHARE_PREF_APP_TOKEN = "SHARE_PREF_APP_TOKEN"

        private var mInstance: SessionManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SessionManager {
            if (mInstance == null) {
                mInstance = SessionManager(mCtx)
            }
            return mInstance as SessionManager
        }

    }
}