package com.jonareas.android.techhub.utils.animation

import android.animation.ObjectAnimator
import android.graphics.Rect
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.BounceInterpolator
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.IdRes
import androidx.annotation.Px
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.view.doOnLayout
import androidx.dynamicanimation.animation.DynamicAnimation.ViewProperty
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.animation.ArgbEvaluatorCompat
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.utils.DEFAULT_ANIMATION_DURATION
import kotlin.math.roundToInt

fun SplashScreen.slideUpOnExit() =
    this.setOnExitAnimationListener {
            splashScreenViewProvider ->
        val splashScreenView = splashScreenViewProvider.view
        val slideUp = ObjectAnimator.ofFloat(
            splashScreenView,
            View.TRANSLATION_Y,
            0f,
            -splashScreenView.height.toFloat(),
        )
        slideUp.interpolator = BounceInterpolator()
        slideUp.duration = DEFAULT_ANIMATION_DURATION
        slideUp.doOnEnd {
            splashScreenViewProvider.remove()
        }
        slideUp.start()
    }

fun View.startCircularRevealFromBottomRight() : Unit = doOnLayout {

    ViewAnimationUtils
        .createCircularReveal(it, right, bottom, 0f, height.toFloat() * 1.5f)
        .apply { duration = DEFAULT_ANIMATION_DURATION }
        .start()
}



/**
 * Map from a [ViewProperty] to an `id` suitable to use as a [View] tag.
 */
@IdRes
private fun getKey(property: ViewProperty): Int {
    return when (property) {
        SpringAnimation.TRANSLATION_X -> R.id.translation_x
        SpringAnimation.TRANSLATION_Y -> R.id.translation_y
        SpringAnimation.TRANSLATION_Z -> R.id.translation_z
        SpringAnimation.SCALE_X -> R.id.scale_x
        SpringAnimation.SCALE_Y -> R.id.scale_y
        SpringAnimation.ROTATION -> R.id.rotation
        SpringAnimation.ROTATION_X -> R.id.rotation_x
        SpringAnimation.ROTATION_Y -> R.id.rotation_y
        SpringAnimation.X -> R.id.x
        SpringAnimation.Y -> R.id.y
        SpringAnimation.Z -> R.id.z
        SpringAnimation.ALPHA -> R.id.alpha
        SpringAnimation.SCROLL_X -> R.id.scroll_x
        SpringAnimation.SCROLL_Y -> R.id.scroll_y
        else -> throw IllegalAccessException("Unknown ViewProperty: $property")
    }
}

fun listenForAllSpringsEnd(
    onEnd: (Boolean) -> Unit,
    vararg springs: SpringAnimation
) = MultiSpringEndListener(onEnd, *springs)

/**
 * A [RecyclerView.ItemDecoration] which adds the given `spacing` to the bottom of any view,
 * unless it is the last item.
 */
class BottomSpacingItemDecoration(
    @Px private val spacing: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val lastItem = parent.getChildAdapterPosition(view) == state.itemCount - 1
        outRect.bottom = if (!lastItem) spacing else 0
    }
}

/**
 * Linearly interpolate between two values
 */
fun lerp(
    startValue: Float,
    endValue: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Float {
    return startValue + fraction * (endValue - startValue)
}

/**
 * Linearly interpolate between two values
 */
fun lerp(
    startValue: Int,
    endValue: Int,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Int {
    return (startValue + fraction * (endValue - startValue)).roundToInt()
}

/**
 * Linearly interpolate between two values when the fraction is in a given range.
 */
fun lerp(
    startValue: Float,
    endValue: Float,
    @FloatRange(
        from = 0.0,
        fromInclusive = true,
        to = 1.0,
        toInclusive = true
    ) startFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) endFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Float {
    if (fraction < startFraction) return startValue
    if (fraction > endFraction) return endValue

    return lerp(startValue, endValue, (fraction - startFraction) / (endFraction - startFraction))
}

/**
 * Linearly interpolate between two values when the fraction is in a given range.
 */
fun lerp(
    startValue: Int,
    endValue: Int,
    @FloatRange(
        from = 0.0,
        fromInclusive = true,
        to = 1.0,
        toInclusive = true
    ) startFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) endFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Int {
    if (fraction < startFraction) return startValue
    if (fraction > endFraction) return endValue

    return lerp(startValue, endValue, (fraction - startFraction) / (endFraction - startFraction))
}

/**
 * Linearly interpolate between two [CornerRounding]s when the fraction is in a given range.
 */
fun lerp(
    startValue: CornerRounding,
    endValue: CornerRounding,
    @FloatRange(
        from = 0.0,
        fromInclusive = true,
        to = 1.0,
        toInclusive = true
    ) startFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) endFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): CornerRounding {
    if (fraction < startFraction) return startValue
    if (fraction > endFraction) return endValue

    return CornerRounding(
        lerp(
            startValue.topLeftRadius,
            endValue.topLeftRadius,
            startFraction,
            endFraction,
            fraction
        ),
        lerp(
            startValue.topRightRadius,
            endValue.topRightRadius,
            startFraction,
            endFraction,
            fraction
        ),
        lerp(
            startValue.bottomRightRadius,
            endValue.bottomRightRadius,
            startFraction,
            endFraction,
            fraction
        ),
        lerp(
            startValue.bottomLeftRadius,
            endValue.bottomLeftRadius,
            startFraction,
            endFraction,
            fraction
        )
    )
}

/**
 * Linearly interpolate between two colors when the fraction is in a given range.
 */
@ColorInt
fun lerpArgb(
    @ColorInt startColor: Int,
    @ColorInt endColor: Int,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Int {
    return ArgbEvaluatorCompat.getInstance().evaluate(
        fraction,
        startColor,
        endColor
    )
}

/**
 * Linearly interpolate between two colors when the fraction is in a given range.
 */
@ColorInt
fun lerpArgb(
    @ColorInt startColor: Int,
    @ColorInt endColor: Int,
    @FloatRange(
        from = 0.0,
        fromInclusive = true,
        to = 1.0,
        toInclusive = true
    ) startFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) endFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Int {
    if (fraction < startFraction) return startColor
    if (fraction > endFraction) return endColor

    return lerpArgb(
        startColor,
        endColor,
        (fraction - startFraction) / (endFraction - startFraction)
    )
}



