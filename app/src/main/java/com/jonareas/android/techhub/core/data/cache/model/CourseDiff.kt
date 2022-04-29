package com.jonareas.android.techhub.core.data.cache.model

import androidx.recyclerview.widget.DiffUtil

object CourseDiff : DiffUtil.ItemCallback<CachedCourse>() {
        override fun areItemsTheSame(oldItem: CachedCourse, newItem: CachedCourse) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CachedCourse, newItem: CachedCourse) = oldItem == newItem
    }
