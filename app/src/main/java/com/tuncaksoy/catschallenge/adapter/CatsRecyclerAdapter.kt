package com.tuncaksoy.catschallenge.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tuncaksoy.catschallenge.R
import com.tuncaksoy.catschallenge.databinding.CatsRecyclerRowBinding
import com.tuncaksoy.catschallenge.model.Cats
import com.tuncaksoy.catschallenge.view.FavoritesPageFragmentDirections
import com.tuncaksoy.catschallenge.view.HomePageFragmentDirections
import com.tuncaksoy.catschallenge.viewmodel.HomePageViewModel
import kotlinx.android.synthetic.main.cats_recycler_row.view.*


/*open class CatsRecyclerAdapter(/*val onFavouriteChanged : (String?, Boolean?) -> Unit*/val catsList: ArrayList<Cats>) :
    RecyclerView.Adapter<CatsRecyclerAdapter.CatsViewHolder>(),CatClickListener {
    var Location = false


    open class CatsViewHolder(var view: CatsRecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.cats_recycler_row, parent, false)
        val view = DataBindingUtil.inflate<CatsRecyclerRowBinding>(inflater,R.layout.cats_recycler_row,parent,false)
        return CatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.view.cat = catsList[position]
        holder.view.listener = this
        holder.view.CatsRecyclerCatFavoritesButton.setOnClickListener{

           // onFavouriteChanged(position.toString(),true)
        }
        /*
        holder.itemView.CatsRecyclerCatGenusText.text = catsList[position].uuid.toString()
        holder.itemView.setOnClickListener {
            if (Location) {
                val action2 =
                    FavoritesPageFragmentDirections.actionFavoritesPageFragmentToDetailPageFragment(
                        catsList.get(position).uuid
                    )
                Navigation.findNavController(it).navigate(action2)
            } else {
                val action = HomePageFragmentDirections.actionHomePageFragmentToDetailPageFragment(
                    catsList.get(position).uuid
                )

                Navigation.findNavController(it).navigate(action)
                Log.d("bb",catsList.get(position).uuid.toString())
            }
        }
        holder.itemView.CatsRecyclerCatImage.downloadImage(
            catsList.get(position).catImageUrl,
            createPlaceholder(holder.itemView.context)
        )
        holder.itemView.CatsRecyclerCatFavoritesButton.setOnClickListener {
            var deneme = catsList[position].uuid
            HomePageViewModel.uuidal(deneme)
        }*/
    }

    override fun getItemCount(): Int {
        return catsList.size
    }

    fun catListRefresh(newCatList: List<Cats>) {
        catsList.clear()
        catsList.addAll(newCatList)
        notifyDataSetChanged()//adapter içerisinde olmasak adapter.notifyData... diye çağırılacaktır
    }

    fun controlLocation(controlLocation: Boolean) {
        Location = controlLocation
    }

    override fun clickCat(view: View) {
        val uuid = view.Cat_uuid.text.toString().toIntOrNull()
        uuid?.let{
            if (Location) {
                val action2 = FavoritesPageFragmentDirections.actionFavoritesPageFragmentToDetailPageFragment(it)
                Navigation.findNavController(view).navigate(action2)
            } else {
                val action = HomePageFragmentDirections.actionHomePageFragmentToDetailPageFragment(it)
                Navigation.findNavController(view).navigate(action)
                Log.d("bb",it.toString())

            }
        }

    }
}*/


