package com.example.mobilenews.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.mobilenews.R
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilenews.data.model.Hit
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

        binding.handler = handler
    }

    /**
     * Interface to handle the swipe refresh
     */
    interface NewsRefreshHandler{
        fun onRefresh()
    }
}