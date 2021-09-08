package com.leva.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation

fun ImageView.loadImage(backgroundImage: String, fallbackImage: String) {
    Glide.with(context)
        .load(backgroundImage)
        .centerCrop()
        .apply(RequestOptions.bitmapTransform(BlurTransformation(4, 3)))
        .fallback(context.getDrawableImageFromFileName(fallbackImage))
        .into(this)
}


