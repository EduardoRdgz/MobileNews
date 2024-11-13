package com.example.mobilenews.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilenews.databinding.ItemNewsBinding
import com.example.mobilenews.domain.model.New
import com.example.mobilenews.utils.TimeUtils

class HomeAdapter : ListAdapter<New, HomeAdapter.HomeViewHolder>(DiffCallback) {

    var onItemClick: ((New) -> Unit)? = null

    // Create a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    // Replace the contents of a view
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val request = getItem(position)
        holder.bind(request)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(request)
        }

    }

    // ViewHolder class
    class HomeViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(request: New) {
            binding.tvTitle.text = request.story_title
            binding.tvAuthor.text = request.author
            binding.tvCreated.text = if (request.created_at?.isEmpty()!!) ""
            else TimeUtils.formatTime(request.created_at)
        }
    }

    // DiffUtil class
    object DiffCallback: DiffUtil.ItemCallback<New>(){
        override fun areItemsTheSame(oldItem: New, newItem: New): Boolean {
            return oldItem.objectID == newItem.objectID
        }
        override fun areContentsTheSame(oldItem: New, newItem: New): Boolean {
            return oldItem == newItem
        }
    }




}