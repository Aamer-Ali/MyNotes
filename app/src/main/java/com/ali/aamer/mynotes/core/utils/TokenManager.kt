package com.ali.aamer.mynotes.core.utils

import android.content.Context
import com.ali.aamer.mynotes.core.utils.Constants.TOKEN_FILE
import com.ali.aamer.mynotes.core.utils.Constants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private var pref = context.getSharedPreferences(TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = pref.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        return pref.getString(USER_TOKEN, null)
    }
}