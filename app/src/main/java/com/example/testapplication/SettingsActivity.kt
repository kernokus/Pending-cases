package com.example.testapplication
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.PopupMenu
import butterknife.ButterKnife
import butterknife.OnClick
import java.util.*


class SettingsActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
       Log.i("Настройки до инстал", loadFromSP(THEME_COLOR))
        installTheme(THEME_COLOR) //установка темы
        Log.d("Настройки после инстал",loadFromSP(THEME_COLOR))
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
                    showRebootDialog(R.id.dark_theme)
                }
                R.id.light_theme -> {
                    showRebootDialog(R.id.light_theme)
                }
            }
            true
        }
        popup.show()
    }


    private fun showRebootDialog(theme:Int) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.need_reboot_dialog)
        val yesBtn = dialog.findViewById(R.id.btnApply) as Button
        val noBtn = dialog.findViewById(R.id.btnCancel) as Button
        yesBtn.setOnClickListener {
            if (theme==R.id.dark_theme) {
                saveInSP(THEME_COLOR, DARK_THEME)
                goReboot()
            }
            else if(theme==R.id.light_theme) {
                saveInSP(THEME_COLOR, LIGHT_THEME)
                goReboot()
            }
            }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }



}