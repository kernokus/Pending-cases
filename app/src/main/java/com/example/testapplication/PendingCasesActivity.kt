package com.example.testapplication

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_pending_cases.*
/*
 Cписок отложенных пользователем дел с датой их установки
Сохранение списка с помощью sharedPreference
*/
class PendingCasesActivity : BaseActivity() {
    private lateinit var myAdapter :AdapterPendingCases

    override fun onCreate(savedInstanceState: Bundle?) {
        installTheme(THEME_COLOR)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_cases)
        ButterKnife.bind(this)
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
        dialog.setContentView(R.layout.pending_dialog)
        val inputCase:EditText=dialog.findViewById(R.id.editText)
        val inputCaseFull:EditText=dialog.findViewById(R.id.editText2)
        val yesBtn = dialog.findViewById(R.id.YesBtn) as Button
        val noBtn = dialog.findViewById(R.id.NoBtn) as Button
        yesBtn.setOnClickListener {
            if (inputCase.text.toString()!=""){
            myAdapter.addAndUpdate(PendingCase(inputCase.text.toString(),null,inputCaseFull.text.toString()))
            dialog.dismiss()}
            else Toast.makeText(baseContext,baseContext.getString(R.string.do_not_enter_anything), Toast.LENGTH_SHORT).show()} //TODO механизм выведения сообщения по-другому
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }




    private fun saveText() {
        val ed: Editor = getPreferences(Context.MODE_PRIVATE).edit()
        val currItems = Gson().toJson(myAdapter.getListValues())
        ed.putString(SAVED_ITEMS,currItems)
        ed.apply()
        myAdapter.clearListRV() //т.к. если приложение свернут,а не закроют,то значения в адаптере+ значения из sp будут совпадать
    }

    private fun loadText() {
        val sPref = getPreferences(Context.MODE_PRIVATE)
        val loadItemsRV: String? = sPref.getString(SAVED_ITEMS, "")
        if (loadItemsRV!=null && loadItemsRV != "") {
        val items:List<PendingCase> = Gson().fromJson(loadItemsRV,Array<PendingCase>::class.java).asList()
        myAdapter.addList(items)
        sPref.edit().remove(SAVED_ITEMS).apply() //очищаю sp после установки
        }
    }

}

