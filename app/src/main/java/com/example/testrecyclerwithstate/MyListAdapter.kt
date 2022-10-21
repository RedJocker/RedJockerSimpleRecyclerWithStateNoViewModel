package com.example.testrecyclerwithstate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyListAdapter(val listModel: List<ModelWithState>, val service: MyListAdapterService): RecyclerView.Adapter<MyListAdapter.ListItem>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItem {
        println("onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListItem(view)
    }

    override fun getItemCount(): Int {
        return listModel.size
    }

    override fun onBindViewHolder(holder: ListItem, position: Int) {
        val modelItem = listModel[position]
        println("onBindViewHolder $position $modelItem ")
        holder.bind(modelItem)
    }


    inner class ListItem(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val textView = itemView.findViewById<TextView>(R.id.itemTextView)
        private val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)

        fun bind(modelItem: ModelWithState) {

            textView.text = modelItem.text
            checkBox.isChecked = modelItem.isSelected

            checkBox.setOnClickListener {
                println("checkBox click $modelItem $adapterPosition")
                modelItem.isSelected = !modelItem.isSelected
                notifyItemChanged(adapterPosition)
                val currentItem = service.getCurrentItem()

                if(currentItem != null) {
                    currentItem.isSelected = false
                    println("unchecking $currentItem")
                    notifyItemChanged(currentItem.index)
                }

                service.setCurrentItem(modelItem)
            }
        }
    }
}