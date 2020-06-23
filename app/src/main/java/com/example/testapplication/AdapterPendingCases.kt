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


class AdapterPendingCases(private val values:ArrayList<String>):RecyclerView.Adapter<AdapterPendingCases.PendingCasesViewHolder>() {
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

         fun bind(item:String) {
            briefDescription?.text=item
             val date = getCurrentDateTime()
             val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")
             currData?.text= dateInString
        }

         private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
             val formatter = SimpleDateFormat(format, locale)
             return formatter.format(this)
         }

         private fun getCurrentDateTime(): Date {
             return Calendar.getInstance().time
         }


         @OnClick(R.id.deleteItem)
         fun deleteAndUpdate(view:View?) {
             Timber.d("DEBUG CLICK")
             delAndUpdate(adapterPosition)

//             values.removeAt(adapterPosition)
          //   notifyDataSetChanged()
         }
//        fun deleteClick(View: View) {
//            values.removeAt(adapterPosition)
//            notifyDataSetChanged()
//        }

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



}