package com.example.testapplication
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installTheme(THEME_COLOR)
        Log.i("Настройки мэин", loadFromSP(THEME_COLOR))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        Log.i("Настройки мэин2", loadFromSP(THEME_COLOR))
    }

    @OnClick(R.id.constrain_pending_cases,R.id.constrain_settings)
    fun clickItem(view: View?) {
        when(view?.id) {
            R.id.constrain_pending_cases -> {
                val intentPending = Intent(this, PendingCasesActivity::class.java)
                startActivity(intentPending)
            }
              R.id.constrain_settings-> {
                  val intentSettings = Intent(this, SettingsActivity::class.java)
                  startActivity(intentSettings)
              }
//            R.id.constrain_camera_x -> {
//                val intentSettings = Intent(this, CameraXActivity::class.java)
//                startActivity(intentSettings)
//
//            }
            }
        }


    }



