package com.tuncaksoy.catschallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuncaksoy.catschallenge.R
import com.tuncaksoy.catschallenge.adapter.CatsListAdapter
import com.tuncaksoy.catschallenge.databinding.FragmentHomePageBinding

import com.tuncaksoy.catschallenge.viewmodel.FavoritesPageViewModel
import kotlinx.android.synthetic.main.fragment_favorites_page.*

class FavoritesPageFragment : Fragment() {

    private lateinit var viewModel: FavoritesPageViewModel
    //lateinit var listAdapter : CatsListAdapter
    //private val listAdapter = CatsListAdapter(viewModel.catss.value.onFavoritesChanged,arrayListOf())
    lateinit var listAdapter : CatsListAdapter
    private var _binding : FragmentHomePageBinding? = null
    private val binding get() = _binding!!
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
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritesPageViewModel::class.java)
        initializeAdapter()

        viewModel.refreshData()
        FavoritesRecyclerView.layoutManager = LinearLayoutManager(context)
        FavoritesRecyclerView.adapter = listAdapter
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.cats.observe(viewLifecycleOwner, Observer {
            it?.let {
                FavoritesRecyclerView.visibility = View.VISIBLE
                listAdapter.catListRefresh(it)
            }
        })
    }
    private fun initializeAdapter(){
        arguments?.let {
            var location = FavoritesPageFragmentArgs.fromBundle(it).favoritesLocationArgs
            listAdapter = CatsListAdapter(ArrayList(0),location, viewModel.catss.value.onFavoritesChanged, arrayListOf())
        }


    }
}