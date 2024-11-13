package com.example.mobilenews.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val deletedItems = HashSet<Int>()

    //This function is called on creation of the viewmodel
    fun getNews() {
        viewModelScope.launch {
            val result = newsUseCase()
            if (result.isNotEmpty()){
                //Filter out deleted items
                val news = result.filter { !deletedItems.contains(it.objectID!!.toInt()) }
                newsModel.postValue(news)
            }
        }
    }

    //This function is called when the user swipes to delete an item
    fun deleteNew(id: Int){
        viewModelScope.launch {
            newsUseCase.deleteNew(id)
            deletedItems.add(id)
        }
    }
}