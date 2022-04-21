package com.jonareas.android.techhub.ui.explore

import android.view.View

@FunctionalInterface
interface CourseViewClick {
    fun onClick(view: View, courseId: Int)
}