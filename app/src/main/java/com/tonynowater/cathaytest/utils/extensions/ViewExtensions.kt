package com.tonynowater.cathaytest.utils.extensions

import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.tonynowater.cathaytest.R

fun AppCompatImageView.reset() {
    (parent as ConstraintLayout).findViewById<CircularProgressIndicator>(R.id.progress_circular).isVisible =
        true
    setImageDrawable(null)
}

fun AppCompatImageView.setErrorImage() {
    (parent as ConstraintLayout).findViewById<CircularProgressIndicator>(R.id.progress_circular).isVisible =
        false
    setImageDrawable(
        ContextCompat.getDrawable(
            context,
            R.drawable.broken_image
        )!!.apply {
            setTint(
                ContextCompat.getColor(
                    context,
                    android.R.color.holo_red_dark
                )
            )
        }
    )
}