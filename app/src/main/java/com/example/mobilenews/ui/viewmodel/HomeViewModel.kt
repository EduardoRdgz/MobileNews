package com.example.mobilenews.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilenews.data.model.Hit
import com.example.mobilenews.data.model.NewsModel
import com.example.mobilenews.domain.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase
) : ViewModel() {

    val newsModel = MutableLiveData<List<Hit>>()

    fun onCreate() {
        viewModelScope.launch {
            val result = newsUseCase()
            if (!result.isNullOrEmpty()) {
                newsModel.postValue(result!!)
            }
        }
    }

}