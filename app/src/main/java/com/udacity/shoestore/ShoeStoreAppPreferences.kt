package com.udacity.shoestore

import android.content.Context
import android.content.SharedPreferences

object ShoeStoreAppPreferences {
    private const val NAME = "shoe_store_pref"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences
    private val IS_LOGGED_IN = Pair("is_logged_in", false)

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var isLoggedIn: Boolean
        get() = preferences.getBoolean(IS_LOGGED_IN.first, IS_LOGGED_IN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGGED_IN.first, value)
        }
}