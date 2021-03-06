package com.charkosoff.siberia.utils

import android.content.Context

object StatusUtils {
    // 1
    fun storeStatus(context: Context, show: Boolean) {
        val preferences = context.getSharedPreferences("showStatus", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor?.putBoolean("show", show)
        editor?.apply()
    }

    // 2
    fun getStatus(context: Context): Boolean {
        val preferences = context.getSharedPreferences("showStatus", Context.MODE_PRIVATE)
        return preferences.getBoolean("show", true)
    }

    fun storeStatusMain(context: Context, show: Boolean) {
        val preferences = context.getSharedPreferences("showStatusMain", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("showMain", show)
        editor.apply()
    }

    fun getStatusMain(context: Context): Boolean {
        val preferences = context.getSharedPreferences("showStatusMain", Context.MODE_PRIVATE)
        return preferences.getBoolean("showMain", true)
    }

    fun storeStatusPager(context: Context, show: Boolean) {
        val preferences = context.getSharedPreferences("showStatusPager", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("showPager", show)
        editor.apply()
    }

    fun getStatusPager(context: Context): Boolean {
        val preferences = context.getSharedPreferences("showStatusPager", Context.MODE_PRIVATE)
        return preferences.getBoolean("showPager", true)

    }

    fun storeStatusPager2(context: Context, show: Boolean) {
        val preferences = context.getSharedPreferences("showStatusPager2", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("showPager2", show)
        editor.apply()
    }

    fun getStatusPager2(context: Context): Boolean {
        val preferences = context.getSharedPreferences("showStatusPager2", Context.MODE_PRIVATE)
        return preferences.getBoolean("showPager2", true)
    }
}