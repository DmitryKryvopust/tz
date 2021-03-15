package com.tz.animal.ui.fragments.cats.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tz.animal.R
import com.tz.animal.dataFlow.network.model.CatResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_cats.view.*


class CatsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    fun bind(
        item: CatResponse.DataItem?,
        onClick: (item: CatResponse.DataItem, position: Int) -> Unit
    ) {
        item?.let {
            with(itemView) {
                tvPosition.text = (adapterPosition + 1).toString()
                tvTitle.text = item.title
                Picasso.get().load(item.url).into(ivCat)
                setOnClickListener { onClick(item, adapterPosition + 1) }
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): CatsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cats, parent, false)
            return CatsViewHolder(view)
        }
    }
}