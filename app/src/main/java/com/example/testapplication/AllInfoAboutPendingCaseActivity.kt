package com.example.testapplication
import android.os.Bundle
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_all_info_pending_case.*

class AllInfoAboutPendingCaseActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installTheme(THEME_COLOR)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_info_pending_case)
        ButterKnife.bind(this)
        briefDescription.text=intent.getStringExtra(BRIEF_DESCRIPTION)
        data.text=intent.getStringExtra(DATA)


        if (intent.getStringExtra(DESCRIPTION)=="") {
            showHide(fullDescriptionTittle)
            showHide(fullDescription)
        }
        else fullDescription.text=intent.getStringExtra(DESCRIPTION)
    }
}