package com.jonareas.android.techhub.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jonareas.android.techhub.core.data.cache.model.CachedTopic
import com.jonareas.android.techhub.core.data.cache.model.CachedTopic.Companion.topicDiff
import com.jonareas.android.techhub.databinding.SearchItemBinding

class SearchAdapter : ListAdapter<CachedTopic, SearchViewHolder>(topicDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SearchViewHolder(
    private val binding: SearchItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(topic: CachedTopic) {
        binding.run {
            this.topic = topic
            executePendingBindings()
        }
    }

}
