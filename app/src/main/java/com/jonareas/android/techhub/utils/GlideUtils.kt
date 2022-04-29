package com.jonareas.android.techhub.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.annotation.StyleRes
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.ShapeAppearancePathProvider
import java.security.MessageDigest

fun loadListener(block: (loaded: Boolean) -> Unit) = GlideDrawableLoadListener(block)

/**
 * A [RequestListener] which executes an action when a [Drawable] loads or fails to load.
 */
class GlideDrawableLoadListener(private val block: (loaded: Boolean) -> Unit) :
    RequestListener<Drawable> {

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        block(true)
        return false
    }

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        block(false)
        return true
    }
}

/**
 * A Glide [Transformation] which applies a [ShapeAppearanceModel] to images.
 */
class ShapeAppearanceTransformation(
    @StyleRes private val shapeAppearanceId: Int
) : Transformation<Bitmap> {

    private var shapeAppearanceModel: ShapeAppearanceModel? = null

    @SuppressLint("RestrictedApi")
    override fun transform(
        context: Context,
        resource: Resource<Bitmap>,
        outWidth: Int,
        outHeight: Int
    ): Resource<Bitmap> {
        val model = shapeAppearanceModel ?: ShapeAppearanceModel.builder(
            context,
            shapeAppearanceId,
            0
        ).build()
            .also { shapeAppearanceModel = it }
        val bitmap = createBitmap(outWidth, outHeight)
        bitmap.applyCanvas {
            val path = Path().apply {
                val bounds = RectF(0f, 0f, outWidth.toFloat(), outHeight.toFloat())
                ShapeAppearancePathProvider().calculatePath(model, 1f, bounds, this)
            }
            val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                shader = BitmapShader(resource.get(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            }
            drawPath(path, paint)
        }
        return BitmapResource(bitmap, Glide.get(context).bitmapPool)
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(javaClass.canonicalName!!.toByteArray())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ShapeAppearanceTransformation

        if (shapeAppearanceId != other.shapeAppearanceId) return false
        if (shapeAppearanceModel != other.shapeAppearanceModel) return false

        return true
    }

    override fun hashCode(): Int {
        var result = shapeAppearanceId
        result = 31 * result + (shapeAppearanceModel?.hashCode() ?: 0)
        return result
    }

}

