package com.jonareas.android.techhub.ui.courses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse.Companion.courseDiff
import com.jonareas.android.techhub.databinding.CourseItemBinding
import com.jonareas.android.techhub.ui.explore.CourseViewClick
import com.jonareas.android.techhub.utils.ShapeAppearanceTransformation
class MyCoursesAdapter : ListAdapter<CachedCourse, MyCoursesAdapter.MyCourseViewHolder>(courseDiff) {

    private object OnClick : CourseViewClick {
        override fun onClick(view: View, courseId: Int) {
            val extras = FragmentNavigatorExtras(
                view to "shared_element"
            )
            val action = MyCoursesFragmentDirections.actionMyCoursesToCourseDetail(courseId)
            view.findNavController().navigate(action, extras)
        }
    }

    private fun animateView(viewToAnimate: View) {
        if (viewToAnimate.animation == null) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.slide_from_bottom_slow)
            viewToAnimate.animation = animation
        }
    }

    private val shapeTransform =
        ShapeAppearanceTransformation(R.style.ShapeAppearance_Owl_SmallComponent)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCourseViewHolder {
        return MyCourseViewHolder(
            CourseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class MyCourseViewHolder(
        private val binding: CourseItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            course: CachedCourse,
            imageTransform: ShapeAppearanceTransformation,
            onClick: CourseViewClick
        ) {
            binding.run {
                this.course = course
                Glide.with(courseImage)
                    .load(course.imageUrl)
                    .placeholder(R.drawable.stroked_course_image_placeholder)
                    .transform(imageTransform)
                    .into(courseImage)
                this.clickHandler = onClick
                animateView(itemView)
                executePendingBindings()
            }
        }
    }

    override fun onBindViewHolder(holder: MyCourseViewHolder, position: Int) {
        holder.bind(getItem(position), shapeTransform, OnClick)
    }

}


