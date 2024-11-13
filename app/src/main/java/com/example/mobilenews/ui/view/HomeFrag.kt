package com.example.mobilenews.ui.view

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.mobilenews.R
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.graphics.Color
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
import kotlin.div

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
        viewModel.getNews()
        setup()
        return binding.root
    }

    private fun setup() {
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.newsModel.observe(viewLifecycleOwner) { news ->
            newsList = news.toMutableList()
            binding.swipeRefresh.isRefreshing = false
            adapter.submitList(newsList)
            Log.d("TAG", "API list: $news")
        }
    }

    private fun setupRecyclerView() {
        adapter = HomeAdapter()
        binding.rvNewsList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNewsList.adapter = adapter

        adapter.onItemClick = { news ->
            findNavController().navigate(
                R.id.action_fragment_home_to_fragment_detail_item,
                bundleOf("url" to news.story_url)
            )
        }

        // Set the handler for the swipe refresh
        binding.handler = handler


        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onChildDraw(
                c: android.graphics.Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

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

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                val deletedCourse: New = newsList.get(viewHolder.adapterPosition)

                // below line is to get the position
                // of the item at that position.
                val position = viewHolder.adapterPosition

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                newsList.removeAt(viewHolder.adapterPosition)

                // below line is to notify our item is removed from adapter.
                adapter.notifyItemRemoved(viewHolder.adapterPosition)

                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
                Toast.makeText(context, "Deleted " + deletedCourse.objectID, Toast.LENGTH_LONG).show()
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(binding.rvNewsList)

    }

    /**
     * Interface to handle the swipe refresh
     */
    interface NewsRefreshHandler{
        fun onRefresh()
    }
}