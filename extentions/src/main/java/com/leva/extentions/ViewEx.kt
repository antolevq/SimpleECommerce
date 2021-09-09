package com.leva.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation


/**
 * Extention function that load a remote or local image into a ImageView
 * @param backgroundImage       A String giving the remote path of the image to show
 * @param fallbackImage         A string represents the local relative path of the fallback image
 *
 * @see                         Glide
 */
fun ImageView.loadImage(backgroundImage: String, fallbackImage: String) {
    Glide.with(context)
        .load(backgroundImage)
        .centerCrop()
        .apply(RequestOptions.bitmapTransform(BlurTransformation(4, 3)))
        .placeholder(context.getDrawableImageFromFileName(fallbackImage))
        .into(this)
}


