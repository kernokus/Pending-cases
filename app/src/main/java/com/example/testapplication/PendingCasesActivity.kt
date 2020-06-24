package com.example.testapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.pending_cases.*


class PendingCasesActivity : AppCompatActivity() {
    lateinit var myAdapter :AdapterPendingCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pending_cases)
        ButterKnife.bind(this);
        recycler_pending.layoutManager =LinearLayoutManager(this)
        val items: ArrayList<String> = arrayListOf()
        myAdapter=AdapterPendingCases(items)
        recycler_pending?.adapter =myAdapter


    }

    @OnClick(R.id.add_content)
    fun clickAddItem(view:View?) {
        myAdapter.addAndUpdate("alo")
    }




}

