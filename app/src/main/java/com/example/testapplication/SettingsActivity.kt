package com.example.testapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick


class SettingsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        setContentView(R.layout.settings)
    }


    @OnClick(R.id.LanguageConstraint,R.id.ColorConstraint)
    fun click(view : View?){
        when(view?.id){
            R.id.LanguageConstraint -> {}
            R.id.ColorConstraint-> {}

        }
    }
}