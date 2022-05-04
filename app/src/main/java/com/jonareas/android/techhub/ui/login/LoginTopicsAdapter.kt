package com.jonareas.android.techhub.ui.login

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.core.data.cache.model.CachedTopic
import com.jonareas.android.techhub.core.data.cache.model.CachedTopic.Companion.topicDiff
import com.jonareas.android.techhub.databinding.OnboardingTopicItemBinding
import com.jonareas.android.techhub.utils.animation.TopicThumbnailTarget

class TopicsAdapter(context: Context) : ListAdapter<CachedTopic, TopicsViewHolder>(topicDiff) {

    private val selectedTint = context.getColor(R.color.topic_tint)
    private val selectedTopLeftCornerRadius =
        context.resources.getDimensionPixelSize(R.dimen.small_component_top_left_radius)
    private val selectedDrawable = context.getDrawable(R.drawable.ic_checkmark)!!


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        val binding = OnboardingTopicItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            root.setOnClickListener {
                it.isActivated = !it.isActivated
            }
        }
        return TopicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) =
        holder.bind(getItem(position), selectedTint, selectedTopLeftCornerRadius, selectedDrawable)

    override fun onViewRecycled(holder: TopicsViewHolder) {
        holder.itemView.rotation = 0f
    }

}

class TopicsViewHolder(
    private val binding: OnboardingTopicItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        topic: CachedTopic,
        @ColorInt selectedTint: Int,
        @Px selectedTopLeftCornerRadius: Int,
        selectedDrawable: Drawable
    ) {
        binding.run {
            this.topic = topic
            Glide.with(topicImage)
                .asBitmap()
                .load(topic.imageUrl)
                .placeholder(R.drawable.course_image_placeholder)
                .into(
                    TopicThumbnailTarget(
                        topicImage,
                        selectedTint,
                        selectedTopLeftCornerRadius,
                        selectedDrawable
                    )
                )
            executePendingBindings()
        }
    }

}