package com.tz.animal.ui.fragments.dogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tz.animal.R
import com.tz.animal.dataFlow.network.model.DogResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_dogs.view.*


class DogsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    fun bind(
        item: DogResponse.DataItem?,
        onClick: (item: DogResponse.DataItem, position: Int) -> Unit
    ) {
        item?.let {
            with(itemView) {
                tvPosition.text = (adapterPosition + 1).toString()
                tvTitle.text = item.title
                Picasso.get().load(item.url).into(ivDog)
                setOnClickListener { onClick(item, adapterPosition + 1) }
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): DogsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_dogs, parent, false)
            return DogsViewHolder(view)
        }
    }
}