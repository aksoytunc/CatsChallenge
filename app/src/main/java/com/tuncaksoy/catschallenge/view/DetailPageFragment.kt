package com.tuncaksoy.catschallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tuncaksoy.catschallenge.R
import com.tuncaksoy.catschallenge.util.createPlaceholder
import com.tuncaksoy.catschallenge.util.downloadImage
import com.tuncaksoy.catschallenge.viewmodel.DetailPageViewModel
import kotlinx.android.synthetic.main.fragment_detail_page.*


class DetailPageFragment : Fragment() {

    private lateinit var viewModel: DetailPageViewModel
    private var catId = 0
    private var location = false
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            catId = DetailPageFragmentArgs.fromBundle(it).catId
            location = DetailPageFragmentArgs.fromBundle(it).location
            position = DetailPageFragmentArgs.fromBundle(it).catPosition
        }
        viewModel = ViewModelProvider(this).get(DetailPageViewModel::class.java)

        if (location){
            viewModel.getRoomData(catId)
        }
        else{
            viewModel.getDataAPI(position)
        }
        observeLiveData()

    }

    fun observeLiveData() {
        viewModel.catLiveData.observe(viewLifecycleOwner, Observer { cat ->
            cat?.let {
                DetailGenusText.text = it.catGenus
                DetailDefinitionText.text = it.catDefinition
                DetailUrlText.text = it.catWikipediaUrl
                context?.let {
                    DetailImage.downloadImage(cat.image.url, createPlaceholder(it))
                }

            }
        })
    }
}