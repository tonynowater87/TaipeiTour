package com.tonynowater.cathaytest.utils.custom_bindings

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.tonynowater.cathaytest.utils.extensions.reset
import com.tonynowater.cathaytest.utils.extensions.setErrorImage

// TODO: display progress when downloading
@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, url: String?) {
    view.reset()
    if (!url.isNullOrEmpty()) {
        Glide.with(view)
            .load(url)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.setErrorImage()
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .centerCrop()
            .into(view)
    } else {
        view.setErrorImage()
    }
}