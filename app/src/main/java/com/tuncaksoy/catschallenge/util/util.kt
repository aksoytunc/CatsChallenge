package com.tuncaksoy.catschallenge.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tuncaksoy.catschallenge.R

/*
fun String.myAddition(parameter : String){
println(parameter)
}*/
fun ImageView.downloadImage(url : String?, placeholder : CircularProgressDrawable){
    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

fun createPlaceholder(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}
@BindingAdapter("android:downloadImage")
fun downloadImage2(view: ImageView, url : String?){
    view.downloadImage(url, createPlaceholder(view.context))
}