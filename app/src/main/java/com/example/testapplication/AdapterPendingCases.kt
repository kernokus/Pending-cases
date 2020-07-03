package com.example.testapplication


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick



class AdapterPendingCases(private val values:ArrayList<PendingCase>):RecyclerView.Adapter<AdapterPendingCases.PendingCasesViewHolder>() {
    override fun getItemCount()=values.size
    private var context: Context? = null
     inner class PendingCasesViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView) {
         init {
             ButterKnife.bind(this, itemView)
         }
            @BindView(R.id.briefDescription)
            @JvmField
            var briefDescription: TextView? = null

             @BindView(R.id.chooseMenu)
             @JvmField
             var deleteItem: ImageView? = null

         fun bind(item: PendingCase) {
             briefDescription?.text=item.description
        }

         @OnClick(R.id.chooseMenu)
         fun deleteAndUpdate(view:View?) {

             if (context!=null && view!=null) {
                 val popup =PopupMenu(context!!, view) //проверить
                 popup.inflate(R.menu.pending_menu)
                 popup.setOnMenuItemClickListener { item: MenuItem? ->
                     when (item!!.itemId) {
                         R.id.menu_saved_info -> {
                             val intentInfo= Intent(briefDescription?.context, AllInfoAboutPendingCaseActivity::class.java)
                             intentInfo.putExtra("data",values[adapterPosition].data)
                                 .putExtra("briefDescription",values[adapterPosition].description)
                                 .putExtra("description",values[adapterPosition].fullDescription)
                             briefDescription?.context?.startActivity(intentInfo)
                         }
                         R.id.menu_saved_delete -> delAndUpdate(adapterPosition)
                     }
                     true
                 }
                 popup.show()
             }
         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingCasesViewHolder {
        context = parent.context
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

    fun  getListValues(): ArrayList<PendingCase> {
        return values
    }

    fun addList(items:List<PendingCase>) {
        values.addAll(items)
    }

    fun getSizeListRV(): Int {return values.size}
    fun clearListRV(){values.clear()}

}