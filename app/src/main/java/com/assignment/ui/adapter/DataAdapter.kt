package com.assignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.R
import com.assignment.model.Row
import com.assignment.ui.DataListActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_data.view.*

class DataAdapter(var list: List<Row>?, var activity: DataListActivity) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list!![position])
    }

    class ViewHolder (v:View) : RecyclerView.ViewHolder(v){
        fun bindItems(row: Row) {
            itemView.txtTitle.text = row.title
            itemView.txtDescription.text = row.description
            if (row.imageHref!=null){
                Glide.with(itemView.context).load(row.imageHref).into(itemView.imgUser)
            }
        }
    }
}