package com.example.mobilenews.ui.view

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.mobilenews.R
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilenews.databinding.FragmentHomeBinding
import com.example.mobilenews.domain.model.New
import com.example.mobilenews.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFrag: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeAdapter
    private var newsList = mutableListOf<New>()

    // Handler for the swipe refresh
    private val handler = object : NewsRefreshHandler {
        override fun onRefresh() {
            viewModel.getNews()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        //viewModel.getNews()
        setup()
        return binding.root
    }

    private fun setup() {
        setupRecyclerView()
        setupObservers()
        setupSwipeToDelete()
    }

    private fun setupObservers() {
        // Observe the news list
        viewModel.newsModel.observe(viewLifecycleOwner) { news ->
            newsList = news.toMutableList()
            binding.swipeRefresh.isRefreshing = false
            adapter.submitList(newsList)
            Log.d("TAG", "API list: $news")
        }
    }

    private fun setupRecyclerView() {
        // Set the recycler view
        adapter = HomeAdapter()
        binding.rvNewsList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNewsList.adapter = adapter

        // Set the handler for the item click
        adapter.onItemClick = { news ->
            findNavController().navigate(
                R.id.action_fragment_home_to_fragment_detail_item,
                bundleOf("url" to news.story_url)
            )
        }

        // Set the handler for the swipe refresh
        binding.handler = handler

    }

    private fun setupSwipeToDelete(){
        // Setting swipe to delete item
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onChildDraw(c: android.graphics.Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                // Handle de swipe to delete attributes
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView

                    val icon = resources.getDrawable(R.drawable.ic_delete, requireContext().theme)

                    val iconMargin = (itemView.height - icon.intrinsicHeight) / 2
                    val iconTop = itemView.top + iconMargin
                    val iconBottom = iconTop + icon.intrinsicHeight
                    val iconLeft = itemView.right - iconMargin - icon.intrinsicWidth
                    val iconRight = itemView.right - iconMargin
                    icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)

                    val paint = Paint()
                    paint.color = resources.getColor(R.color.red, requireContext().theme)

                    if (dX > 0) { // Swiping to the right
                        c.drawRect(itemView.left.toFloat(), itemView.top.toFloat(), dX, itemView.bottom.toFloat(), paint)
                    } else { // Swiping to the left
                        c.drawRect(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat(), paint)
                        icon.draw(c) // Draw icon when swiping left

                    }
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedCourse: New = newsList.get(viewHolder.adapterPosition)

                // Get the position of the item at that position.
                val position = viewHolder.adapterPosition

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                newsList.removeAt(viewHolder.adapterPosition)

                // Notify our item is removed from adapter.
                adapter.notifyItemRemoved(viewHolder.adapterPosition)

                // Display our toast
                Toast.makeText(context, "Deleted " + deletedCourse.author +  " post", Toast.LENGTH_LONG).show()
            }
        }).attachToRecyclerView(binding.rvNewsList)
    }

    /**
     * Interface to handle the swipe refresh
     */
    interface NewsRefreshHandler{
        fun onRefresh()
    }
}