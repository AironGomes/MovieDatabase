package com.airongomes.moviedatabase.extensions

import android.widget.ImageView
import androidx.core.net.toUri
import com.airongomes.moviedatabase.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(imagePath: String, width: String = "original") {
    val urlImage = context.getString(R.string.image_url, width, imagePath)
    val uri = urlImage.toUri().buildUpon().scheme("https").build()
    Glide.with(context)
        .load(uri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image)//TODO
        )
        .into(this)
}