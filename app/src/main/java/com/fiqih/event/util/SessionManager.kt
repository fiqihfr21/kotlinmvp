package com.fiqih.event.util

import android.content.Context
import android.util.Log
import com.fiqih.event.model.UserID

class SessionManager private constructor(private val context: Context){

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: UserID
        get() {
            val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return UserID(
                sharedPreferences.getInt("id", 0),
                sharedPreferences.getString("token", null)
            )
        }


    fun saveUser(user: UserID?) {

        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("id", user!!.id)
        editor.putString("token", user!!.token)
        editor.commit()

    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor.commit()
        Log.i("introsession", "--1")
    }

    val isFirstTimeLaunch: Boolean
        get() {
            val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            Log.i("introsession2", sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, false).toString())
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