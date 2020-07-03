package com.example.testapplication

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import butterknife.ButterKnife
import butterknife.OnClick

import java.util.*


open class SettingsActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppThemeLight)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)
        ButterKnife.bind(this)
    }


    @OnClick(R.id.LanguageConstraint,R.id.ColorConstraint)
    fun click(view : View?) {
        when(view?.id) {
            R.id.LanguageConstraint -> {
                showLanguageMenu(view)
            }
            R.id.ColorConstraint ->{showThemeMenu(view)}
        }
    }


    private fun showLanguageMenu(view: View) {
        val popup: PopupMenu = PopupMenu(this, view) //проверить
        popup.inflate(R.menu.language_menu)
        popup.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.english_language -> {
                    updateLocale(Locale.ENGLISH)
                }
                R.id.russian_language -> {
                    val localeRU = Locale("ru")
                    updateLocale(localeRU)}
            }
            true
        }
        popup.show()
    }

    private fun showThemeMenu(view: View) {
        val popup: PopupMenu = PopupMenu(this, view) //проверить
        popup.inflate(R.menu.theme_menu)
        popup.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.dark_theme -> {
                   //
                }
                R.id.light_theme -> {
                    //
                }
            }
            true
        }
        popup.show()
    }



}