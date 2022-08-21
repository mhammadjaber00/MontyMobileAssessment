package com.mohammadjaber.montymobileassessmentapplication.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mohammadjaber.montymobileassessmentapplication.common.TimeAgo.getTimeAgo

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

@BindingAdapter("getTime")
fun getTime(view: TextView, time: String) {
    view.text = getTimeAgo(time)
}
