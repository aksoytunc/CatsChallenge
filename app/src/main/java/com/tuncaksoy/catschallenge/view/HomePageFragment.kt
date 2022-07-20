package com.tuncaksoy.catschallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuncaksoy.catschallenge.R
import com.tuncaksoy.catschallenge.adapter.CatsListAdapter
import com.tuncaksoy.catschallenge.viewmodel.HomePageViewModel
import kotlinx.android.synthetic.main.fragment_home_page.*


open class HomePageFragment : Fragment() {

    private lateinit var viewModel: HomePageViewModel
    private val recyclerCatsAdapter = CatsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HomeFavoritesButton.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToFavoritesPageFragment(true)
            Navigation.findNavController(it).navigate(action)
        }
        viewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)
        viewModel.refreshData()
        HomeRecyclerView.layoutManager = LinearLayoutManager(context)
        HomeRecyclerView.adapter = recyclerCatsAdapter
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.cats.observe(viewLifecycleOwner, Observer {
            it?.let {
                HomeRecyclerView.visibility = View.VISIBLE
                recyclerCatsAdapter.catListRefresh(it)
            }
        })

    }
}