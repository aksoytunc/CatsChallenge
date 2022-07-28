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
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import kotlinx.android.synthetic.main.fragment_splash.view.*


class CatsListAdapter(
    var favoritesNumberList : ArrayList<Int>,
    var location: Boolean,
    var onFAvoritesChanged: (Int?, String?, Boolean?) -> Unit,
    val catsList: ArrayList<Cats>
) : ListAdapter<Cats, CatsListAdapter.CatsListViewHolder>(CatsDiffCallback()), CatClickListener {
    var favorites = false
    var i = 0
    var positionId = 0
    class CatsListViewHolder(var view: CatsRecyclerRowBinding) :
        RecyclerView.ViewHolder(view.root) {

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
        positionId = position
        //holder.view.executePendingBindings()
        //Log.d("tft", favoritesNumberList.toString())
        i=0
        while (i<favoritesNumberList.size) {
            Log.d("tftt", favoritesNumberList[i].toString()+"--"+position.toString())
            if (favoritesNumberList[i] == position) {
                i=favoritesNumberList.size
                holder.view.CatsRecyclerCatFavoritesButton.setImageResource(R.drawable.favorites_yes)
            }
            else{
                holder.view.CatsRecyclerCatFavoritesButton.setImageResource(R.drawable.favorites_no)
            }
            i++
        }
        holder.view.CatsRecyclerCatFavoritesButton.setOnClickListener {
            onFAvoritesChanged(position, catsList[position].catGenus, favorites)
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
        positionId =
        Log.d("positionId",positionId.toString())
        //TODO
        uuid?.let {
            if (location) {
                val action2 =
                    FavoritesPageFragmentDirections.actionFavoritesPageFragmentToDetailPageFragment(
                        it,location
                    )
                Navigation.findNavController(view).navigate(action2)
            } else {
                val action =
                    HomePageFragmentDirections.actionHomePageFragmentToDetailPageFragment(it,location,positionId)
                println(it)
                Navigation.findNavController(view).navigate(action)
                Log.d("bb", it.toString())
            }
        }
    }
}
