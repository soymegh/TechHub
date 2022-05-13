package com.jonareas.android.techhub.animation.animators

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.Px
import com.bumptech.glide.request.target.ImageViewTarget

/**
 * A Glide [com.bumptech.glide.request.target.Target] which wraps a loaded [Bitmap] in a
 * [TopicThumbnailDrawable].
 */
class TopicThumbnailTarget(
    imageView: ImageView,
    @ColorInt private val selectedTint: Int,
    @Px private val selectedTopLeftCornerRadius: Int,
    private val selectedDrawable: Drawable
) : ImageViewTarget<Bitmap>(imageView) {

    override fun setResource(resource: Bitmap?) {
        resource?.let {
            val drawable = (currentDrawable as? TopicThumbnailDrawable) ?: TopicThumbnailDrawable(
                selectedTint,
                selectedTopLeftCornerRadius,
                selectedDrawable
            )
            drawable.bitmap = it
            setDrawable(drawable)
        }
    }
}
