package com.example.testapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class PendingCasesActivity : AppCompatActivity() {

    @BindView(R.id.recycler_pending)
    @JvmField
    var recyclerView: RecyclerView? = null

    @BindView(R.id.constrain_pending_cases)
    @JvmField
    var btnAdd: ImageView? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pending_cases)
        ButterKnife.bind(this);
        recyclerView?.layoutManager =LinearLayoutManager(this)
        val items: ArrayList<String> = arrayListOf("1","2","3","4","1","2","3","4","1","2","3","4")
        val myAdapter=AdapterPendingCases(items)
        recyclerView?.adapter =myAdapter
    }
}
//
//@OnClick(R.id.add_content)
//fun addItem() {
//
//}