package com.jonareas.android.techhub.utils

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen

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
        slideUp.duration = 1000L
        slideUp.doOnEnd {
            splashScreenViewProvider.remove()
        }
        slideUp.start()
    }

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}