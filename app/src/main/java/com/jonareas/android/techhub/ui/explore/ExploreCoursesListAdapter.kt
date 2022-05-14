package com.jonareas.android.techhub.ui.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse.Companion.courseDiff
import com.jonareas.android.techhub.databinding.FeaturedItemBinding

class ExploreCoursesListAdapter(
    private val onClick: CourseViewClick,
) : ListAdapter<CachedCourse, ExploreCoursesListAdapter.FeaturedViewHolder>(courseDiff) {

    inner class FeaturedViewHolder(
        private val binding: FeaturedItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: CachedCourse, onClick: CourseViewClick) {
            binding.run {
                this.course = course
                clickHandler = onClick
                animateView(itemView)
                executePendingBindings()
            }
        }
    }

    enum class ScrollDirection {
        UP, DOWN
    }

    var scrollDirection = ScrollDirection.DOWN

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ExploreCoursesListAdapter.FeaturedViewHolder = FeaturedViewHolder(
        FeaturedItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(
        holder: ExploreCoursesListAdapter.FeaturedViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position), onClick)
    }

    private fun animateView(viewToAnimate: View) {
        if (viewToAnimate.animation == null) {
            val animId =
                if (scrollDirection == ScrollDirection.DOWN) R.anim.slide_from_bottom else R.anim.slide_from_top
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, animId)
            viewToAnimate.animation = animation
        }

    }

}
