package com.example.myapplication.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.AmbientContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.myapplication.R
import com.example.myapplication.network.Network
import com.example.myapplication.ui.model.Feed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val DEFAULT_IMAGE = R.drawable.wallpaper

@Composable
fun loadImage(url:String,@DrawableRes dr:Int):MutableState<Bitmap?>{
    val bitmapState:MutableState<Bitmap?> = mutableStateOf(null)
    Glide.with(AmbientContext.current)
        .asBitmap()
        .load(dr)
        .into(object :CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })

    Glide.with(AmbientContext.current)
        .asBitmap()
        .load(url)
        .into(object :CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
    return bitmapState
}


class ViewMod: ViewModel(){
    private var _todoItems = MutableLiveData(listOf<Feed>())
    val todoItems: LiveData<List<Feed>> = _todoItems

    fun getList(){
        Network.service.listRepos().enqueue(object : Callback<List<Feed>> {

            override fun onResponse(call: Call<List<Feed>>, response: Response<List<Feed>>) {
                response.body()?.let { it ->
                    _todoItems.value = it.toMutableList()
                }
            }

            override fun onFailure(call: Call<List<Feed>>, t: Throwable) {

            }
        })
    }
}
