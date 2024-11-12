package com.example.mobilenews.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilenews.databinding.FragmentHomeBinding
import com.example.mobilenews.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFrag: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.onCreate()
        setup()
        return binding.root
    }

    private fun setup() {
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.newsModel.observe(viewLifecycleOwner) { news ->
            adapter.submitList(news)
            Log.d("TAG", "API list: $news")
        }
    }

    private fun setupRecyclerView() {
        adapter = HomeAdapter()
        binding.rvNewsList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNewsList.adapter = adapter
    }

}