package com.jonareas.android.techhub.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse
import com.jonareas.android.techhub.core.data.cache.model.CourseDiff
import com.jonareas.android.techhub.databinding.FeaturedItemBinding

class ExploreCoursesListAdapter(
    private val onClick: CourseViewClick,
) : ListAdapter<CachedCourse, FeaturedViewHolder>(CourseDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        return FeaturedViewHolder(
            FeaturedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}

class FeaturedViewHolder(
    private val binding: FeaturedItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(course: CachedCourse, onClick: CourseViewClick) {
        binding.run {
            this.course = course
            clickHandler = onClick
            executePendingBindings()
        }
    }
}
