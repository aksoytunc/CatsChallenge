package com.tuncaksoy.catschallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuncaksoy.catschallenge.R
import com.tuncaksoy.catschallenge.databinding.CatsRecyclerRowBinding
import com.tuncaksoy.catschallenge.model.Cats


class CatsListAdapter() : ListAdapter<Cats, CatsListAdapter.CatsListViewHolder>(CatsDiffCallback()) {

    class CatsListViewHolder(var view: CatsRecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CatsRecyclerRowBinding>(
            inflater,
            R.layout.cats_recycler_row,
            parent,
            false
        )
        return CatsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatsListViewHolder, position: Int) {
        val item = getItem(position)
        holder.view.cat = item
        holder.view.executePendingBindings()
    }

    class CatsDiffCallback : DiffUtil.ItemCallback<Cats>() {

        override fun areItemsTheSame(oldItem: Cats, newItem: Cats): Boolean {
            return oldItem.catGenus == newItem.catGenus && oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: Cats, newItem: Cats): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }

    }

    fun catListRefresh(newCatList: List<Cats>) {

    }
}
