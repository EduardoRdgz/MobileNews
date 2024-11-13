package com.example.mobilenews.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobilenews.databinding.FragmentDetailItemBinding

class DetailFrag: Fragment() {
    private lateinit var binding: FragmentDetailItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailItemBinding.inflate(inflater, container, false)
        val url = arguments?.getString("url")
        binding.wvDetail.loadUrl(url!!)
        return binding.root
    }
}