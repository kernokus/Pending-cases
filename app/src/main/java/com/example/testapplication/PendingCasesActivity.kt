package com.example.testapplication
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.gson.Gson
import kotlinx.android.synthetic.main.pending_cases.*

/*
Cписок отложенных пользователем дел с датой их установки
Сохранение списка с помощью sharedPreference(sql? mb)
*/
class PendingCasesActivity : AppCompatActivity() {
    private lateinit var myAdapter :AdapterPendingCases
    private val SAVED_ITEMS = "savedItems"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pending_cases)
        ButterKnife.bind(this);
        recycler_pending.layoutManager =LinearLayoutManager(this)
        val items: ArrayList<PendingCase> = arrayListOf()
        myAdapter=AdapterPendingCases(items)
        recycler_pending?.adapter =myAdapter
    }

    override fun onStop() {
        super.onStop()
        if (myAdapter.getSizeListRV()!=0)  saveText() //сохранение в SP
    }

    override fun onStart() {
        super.onStart()
        loadText() //достаю из SP
    }

    @OnClick(R.id.add_content)
    fun clickAddItem(view:View?) {
        showDialog()
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setTitle("Hello")
        dialog.setContentView(R.layout.pending_dialog)
        val inputCase:EditText=dialog.findViewById(R.id.editText)
        val yesBtn = dialog.findViewById(R.id.YesBtn) as Button
        val noBtn = dialog.findViewById(R.id.NoBtn) as Button
        yesBtn.setOnClickListener {
            myAdapter.addAndUpdate(PendingCase(inputCase.text.toString(),null))
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun saveText() {
        val sPref = getPreferences(Context.MODE_PRIVATE)
        val ed: Editor = sPref.edit()
        val list: List<PendingCase> = myAdapter.getlistValues()
        val currItems = Gson().toJson(list)
        ed.putString(SAVED_ITEMS,currItems)
        ed.apply()
    }

    private fun loadText() {
        val sPref = getPreferences(Context.MODE_PRIVATE)
        val loadItemsRV: String? = sPref.getString(SAVED_ITEMS, "")
        if (loadItemsRV!=null && loadItemsRV != "") {
        val items:List<PendingCase> = Gson().fromJson(loadItemsRV,Array<PendingCase>::class.java).asList()
        myAdapter.addList(items)
        }
    }

}

