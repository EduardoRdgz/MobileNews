package com.example.mobilenews.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilenews.data.model.Hit
import com.example.mobilenews.databinding.ItemNewsBinding
import com.example.mobilenews.utils.TimeUtils

class HomeAdapter : ListAdapter<Hit, HomeAdapter.HomeViewHolder>(DiffCallback) {

    var onItemClick: ((Hit) -> Unit)? = null

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
        fun bind(request: Hit) {
            binding.tvTitle.text = request.story_title
            binding.tvAuthor.text = request.author
            binding.tvCreated.text = TimeUtils.formatTime(request.created_at)
        }
    }

    // DiffUtil class
    object DiffCallback: DiffUtil.ItemCallback<Hit>(){
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.title == newItem.title
        }
        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }
    }




}