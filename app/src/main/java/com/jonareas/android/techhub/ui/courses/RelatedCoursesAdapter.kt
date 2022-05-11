package com.jonareas.android.techhub.ui.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse.Companion.courseDiff
import com.jonareas.android.techhub.databinding.RelatedCourseItemBinding
import com.jonareas.android.techhub.ui.explore.CourseViewClick

class RelatedCoursesAdapter( private val onClick: CourseViewClick) : ListAdapter<CachedCourse, RelatedViewHolder>(courseDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedViewHolder {
        val binding = RelatedCourseItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RelatedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RelatedViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

}

class RelatedViewHolder(
    private val binding: RelatedCourseItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(course: CachedCourse, onClick: CourseViewClick) {
        binding.run {
            this.course = course
            clickHandler = onClick
            executePendingBindings()
        }
    }

}