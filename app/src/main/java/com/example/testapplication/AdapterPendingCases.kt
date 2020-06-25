package com.example.testapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AdapterPendingCases(private val values:ArrayList<PendingCase>):RecyclerView.Adapter<AdapterPendingCases.PendingCasesViewHolder>() {
    override fun getItemCount()=values.size

     inner class PendingCasesViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView) {
         init {
             ButterKnife.bind(this, itemView)
         }
            @BindView(R.id.briefDescription)
            @JvmField
            var briefDescription: TextView? = null

             @BindView(R.id.currData)
             @JvmField
             var currData: TextView? = null

             @BindView(R.id.deleteItem)
             @JvmField
             var deleteItem: ImageView? = null

         fun bind(item: PendingCase) {
             briefDescription?.text=item.description
             currData?.text= item.data
        }

         @OnClick(R.id.deleteItem)
         fun deleteAndUpdate(view:View?) {
             Timber.d("DEBUG CLICK")
             delAndUpdate(adapterPosition)
         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingCasesViewHolder{
        return PendingCasesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pending_cases, parent, false))}

    override fun onBindViewHolder(holder: PendingCasesViewHolder, position: Int) {
        holder.bind(values[position])
    }


    fun delAndUpdate(position: Int) {
        values.removeAt(position)
        notifyDataSetChanged()
    }

    fun addAndUpdate(item: PendingCase) {
        values.add(item)
        notifyDataSetChanged()
    }

    fun  getlistValues(): ArrayList<PendingCase> {
        return values
    }

    fun addList(items:List<PendingCase>) {
        values.addAll(items)
    }

    fun getSizeListRV(): Int {return values.size}


}