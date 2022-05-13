package com.jonareas.android.techhub.utils

import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.jonareas.android.techhub.ui.MainActivity

val Fragment.mainActivity : MainActivity
    get() = activity as MainActivity

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

/**
 * Retrieves the text of an [EditText] as a Kotlin String
 */
val EditText.string : String
    get() = text.toString()