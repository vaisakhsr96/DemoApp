package com.example.demoapp.ui.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.data.model.immerSphere.SearchListModel
import com.example.demoapp.data.model.MovieModel.MovieListModel
import com.example.demoapp.data.model.UserItemsModel.UserItems
import kotlinx.coroutines.launch


class ItemViewModel : ViewModel() {
     val repository: ItemRepository?=ItemRepository()
    private var _items = MutableLiveData<UserItems>()
    private var movieList = MutableLiveData<MovieListModel>()
    val movieListItems : LiveData<MovieListModel> get() = movieList
    val items: LiveData<UserItems> get() = _items

    private var searchListItems = MutableLiveData<SearchListModel>()
    val searchList: LiveData<SearchListModel> get() = searchListItems
    private var dependency: String? = null

    fun loadItems() {
        viewModelScope.launch {
           // movieList.value = repository?.getItems()
            searchListItems.value = repository?.getSearchList("0","15")
        }
    }


}

