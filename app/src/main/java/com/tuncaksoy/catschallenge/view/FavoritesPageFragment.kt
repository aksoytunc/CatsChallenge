package com.tuncaksoy.catschallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.tuncaksoy.catschallenge.R
import com.tuncaksoy.catschallenge.adapter.CatsListAdapter

import com.tuncaksoy.catschallenge.viewmodel.FavoritesPageViewModel
import kotlinx.android.synthetic.main.fragment_favorites_page.*

class FavoritesPageFragment : Fragment() {

    private lateinit var viewModel: FavoritesPageViewModel
    private val recyclerCatsAdapter = CatsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            var location = FavoritesPageFragmentArgs.fromBundle(it).favoritesLocationArgs
            //recyclerCatsAdapter.controlLocation(location)
        }
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritesPageViewModel::class.java)
        viewModel.refreshData()
        FavoritesRecyclerView.layoutManager = LinearLayoutManager(context)
        FavoritesRecyclerView.adapter = recyclerCatsAdapter
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.cats.observe(viewLifecycleOwner, Observer {
            it?.let {
                FavoritesRecyclerView.visibility = View.VISIBLE
                recyclerCatsAdapter.catListRefresh(it)
            }
        })
    }
}