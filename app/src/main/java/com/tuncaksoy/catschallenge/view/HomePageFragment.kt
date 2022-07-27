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
import com.tuncaksoy.catschallenge.adapter.CatsRecyclerAdapter
import com.tuncaksoy.catschallenge.databinding.FragmentHomePageBinding
import com.tuncaksoy.catschallenge.viewmodel.HomePageViewModel
import kotlinx.android.synthetic.main.fragment_home_page.*


open class HomePageFragment : Fragment() {

    private lateinit var viewModel: HomePageViewModel
    lateinit var listAdapter : CatsListAdapter
    private var _binding : FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    //private val listAdapter = CatsRecyclerAdapter(arrayListOf())

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
        initializeAdapter()
        viewModel.refreshData()
        HomeRecyclerView.layoutManager = LinearLayoutManager(context)
        HomeRecyclerView.adapter = listAdapter
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.cats.observe(viewLifecycleOwner, Observer {
            it?.let {
                HomeRecyclerView.visibility = View.VISIBLE
                listAdapter.catListRefresh(it)
            }
        })

    }

    private fun initializeAdapter(){
        val location = false
        listAdapter = CatsListAdapter(viewModel.favoritesNumberList,location,viewModel.catss.value.onFavoritesChanged, arrayListOf())
    }
}