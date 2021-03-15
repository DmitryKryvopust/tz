package com.tz.animal.ui.fragments.dogs.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tz.animal.dataFlow.network.model.DogResponse


class DogsAdapter(private val onClick: (item: DogResponse.DataItem, position: Int) -> Unit) :
    RecyclerView.Adapter<DogsViewHolder>() {

    var catsList = mutableListOf<DogResponse.DataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder =
        DogsViewHolder.create(parent)

    override fun getItemCount(): Int = catsList.size

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        holder.bind(catsList[position], onClick)
    }

    fun setList(data: List<DogResponse.DataItem>) {
        catsList = data.toMutableList()
        notifyDataSetChanged()
    }
}