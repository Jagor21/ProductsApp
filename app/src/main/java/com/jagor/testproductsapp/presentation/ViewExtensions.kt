package com.jagor.testproductsapp.presentation

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jagor.testproductsapp.GlideApp
import com.jagor.testproductsapp.R

fun ImageView.loadImageCenterCrop(uri: String) {
    val options = RequestOptions()
        .error(R.drawable.error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
    GlideApp.with(this.context)
        .load(uri)
        .thumbnail(Glide.with(this.context).load(R.drawable.thumbnail))
        .apply(options)
        .centerCrop()
        .into(this)
}

fun View.changeVisibility(visible: Boolean) {
    this.visibility = when {
        visible -> View.VISIBLE
        else -> View.INVISIBLE
    }
}

fun View.changeVisibilityWithGone(visible: Boolean) {
    this.visibility = when {
        visible -> View.VISIBLE
        else -> View.GONE
    }
}

fun Fragment.makeToast(message: String) = activity?.makeToast(message)

fun Context.makeToast(textRes: String): Toast = Toast
    .makeText(this, textRes, Toast.LENGTH_SHORT).apply { show() }
