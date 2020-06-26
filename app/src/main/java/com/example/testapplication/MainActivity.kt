package com.example.testapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this);
    }

    @OnClick(R.id.constrain_pending_cases,R.id.constrain_tasks,R.id.constrain_settings,R.id.constrainML)
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
            R.id.constrainML -> {
                val intentSettings = Intent(this, MLActivity::class.java)
                startActivity(intentSettings)
            }

            }
        }

    }



