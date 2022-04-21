package com.jonareas.android.techhub.utils.animation

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.annotation.IdRes
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.DynamicAnimation.ViewProperty
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.utils.defaultAnimationDuration

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
        slideUp.duration = defaultAnimationDuration
        slideUp.doOnEnd {
            splashScreenViewProvider.remove()
        }
        slideUp.start()
    }


/**
 * An extension function which creates/retrieves a [SpringAnimation] and stores it in the [View]s
 * tag.
 */
fun View.spring(
    property: DynamicAnimation.ViewProperty,
    stiffness: Float = 200f,
    damping: Float = 0.3f,
    startVelocity: Float? = null
): SpringAnimation {
    val key = getKey(property)
    var springAnim = getTag(key) as? SpringAnimation?
    if (springAnim == null) {
        springAnim = SpringAnimation(this, property).apply {
            spring = SpringForce().apply {
                this.dampingRatio = damping
                this.stiffness = stiffness
                startVelocity?.let { setStartVelocity(it) }
            }
        }
        setTag(key, springAnim)
    }
    return springAnim
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

/**
 * Changes the visibility of a [View] to visible on screen; the default value.
 */
fun View.visible() {
    this.visibility = View.VISIBLE
}

/**
 * Changes the visibility of a [View] to be completely hidden, as if the view had not been added.
 */
fun View.gone() {
    this.visibility = View.GONE
}
/**
 * Changes the visibility of a [View] to not displayed, but taken into account during layout (space is left for it).
 */
fun View.invisible() {
    this.visibility = View.INVISIBLE
}

