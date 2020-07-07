package com.example.testapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity

open class BaseActivity : LocaleAwareCompatActivity() {
    companion object {
        val THEME_COLOR = "themeColor"
        val DARK_THEME = "darkTheme"
        val LIGHT_THEME = "LightTheme"
    }


    fun saveInSP(key:String,value:String) {
        val ed: SharedPreferences.Editor = getSharedPreferences("temp",Context.MODE_PRIVATE).edit()
        ed.putString(key,value)
        ed.apply()
    }

    fun loadFromSP(key:String) : String? {
        val sPref = getSharedPreferences("temp",Context.MODE_PRIVATE)
        return sPref.getString(key, "")
    }

    fun installTheme(key:String) {
        val themeTemp:String?=loadFromSP(key)
        if (themeTemp=="") {
            setTheme(R.style.AppThemeLight)
            Log.i("installTheme","Пустая строка темы")}
        else if (themeTemp== DARK_THEME) setTheme(R.style.AppTheme)
        else if (themeTemp== LIGHT_THEME) setTheme(R.style.AppThemeLight)
        Log.i("installTheme",themeTemp)
    }

     fun goReboot() {
        val intentReboot= Intent(this,MainActivity::class.java)
        intentReboot.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intentReboot.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intentReboot)
    }


}