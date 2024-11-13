package com.example.mobilenews.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilenews.data.model.Hit
import com.example.mobilenews.domain.GetNewsUseCase
import com.example.mobilenews.domain.model.New
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase
) : ViewModel() {

    val newsModel = MutableLiveData<List<New>>()

    //This function is called on creation of the viewmodel
    fun getNews() {
        viewModelScope.launch {
            val result = newsUseCase()
            if (result.isNotEmpty())
                newsModel.postValue(result!!)
        }
    }
}