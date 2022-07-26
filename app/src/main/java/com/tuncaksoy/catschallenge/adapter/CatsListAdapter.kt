package com.tuncaksoy.catschallenge.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tuncaksoy.catschallenge.R
import com.tuncaksoy.catschallenge.databinding.CatsRecyclerRowBinding
import com.tuncaksoy.catschallenge.model.Cats
import com.tuncaksoy.catschallenge.servis.CatDatabase
import com.tuncaksoy.catschallenge.view.FavoritesPageFragmentDirections
import com.tuncaksoy.catschallenge.view.HomePageFragmentDirections
import com.tuncaksoy.catschallenge.viewmodel.HomePageViewModel
import kotlinx.android.synthetic.main.cats_recycler_row.view.*


class CatsListAdapter(var onFAvoritesChanged : (Int?, String?,Boolean?) -> Unit ,val catsList :ArrayList<Cats>) : ListAdapter<Cats,CatsListAdapter.CatsListViewHolder>(CatsDiffCallback()),CatClickListener {

    var location = false
    var favorites = false

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

        holder.view.cat = catsList[position]
        holder.view.listener = this
        //holder.view.executePendingBindings()
        val item = catsList[position]


        holder.view.CatsRecyclerCatFavoritesButton.setOnClickListener {
            if (item.catFavorites==true)
                favorites = false
            else
                favorites = true
                catsList[position].catFavorites=true
            onFAvoritesChanged(position,catsList[position].catGenus,favorites)
        }
    }

    class CatsDiffCallback : DiffUtil.ItemCallback<Cats>() {

        override fun areItemsTheSame(oldItem: Cats, newItem: Cats): Boolean {
            return oldItem.catGenus == newItem.catGenus // && oldItem.catFavorites == newItem.catFavorites
        }

        override fun areContentsTheSame(oldItem: Cats, newItem: Cats): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }

    }

    fun catListRefresh(newCatList: List<Cats>) {
        catsList.clear()
        catsList.addAll(newCatList)
        notifyDataSetChanged()//adapter içerisinde olmasak adapter.notifyData... diye çağırılacaktır
    }
    override fun getItemCount(): Int {
        return catsList.size
    }

    override fun clickCat(view: View) {
        val uuid = view.Cat_uuid.text.toString().toIntOrNull()
        uuid?.let{
            if (!location) {
                val action2 = FavoritesPageFragmentDirections.actionFavoritesPageFragmentToDetailPageFragment(it)
                Navigation.findNavController(view).navigate(action2)
            } else {
                val action = HomePageFragmentDirections.actionHomePageFragmentToDetailPageFragment(it)
                println(it)
                Navigation.findNavController(view).navigate(action)
                Log.d("bb",it.toString())
            }
        }
    }
}
