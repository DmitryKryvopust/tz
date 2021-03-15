package com.tz.animal.ui.fragments.cats.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tz.animal.dataFlow.network.model.CatResponse

class CatsAdapter(private val onClick: (item: CatResponse.DataItem, position: Int) -> Unit) :
    RecyclerView.Adapter<CatsViewHolder>() {

    var catsList = mutableListOf<CatResponse.DataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder = CatsViewHolder.create(parent)

    override fun getItemCount(): Int = catsList.size

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.bind(catsList[position],onClick)
    }

    fun setList(data : List<CatResponse.DataItem>){
        catsList = data.toMutableList()
        notifyDataSetChanged()
    }
}