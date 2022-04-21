package com.jonareas.android.techhub.utils.animation

import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.BounceInterpolator
import androidx.annotation.IdRes
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.view.doOnLayout
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.DynamicAnimation.ViewProperty
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.utils.DEFAULT_ANIMATION_DURATION

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


