package com.example.demoapp.ui.Home

import com.example.demoapp.data.api.RestServiceBuilder
import com.example.demoapp.data.model.immerSphere.SearchListModel
import com.example.demoapp.data.model.MovieModel.MovieListModel
import com.example.demoapp.data.model.UserItemsModel.UserItems

class ItemRepository {
    private lateinit var items: UserItems

    private lateinit var movieListModel: MovieListModel
    private lateinit var searchListModel: SearchListModel

    suspend fun getItems(): MovieListModel {
        // Make API call or fetch data from a local database
        // Return the list of items


            try {
                movieListModel = RestServiceBuilder.apiService?.getMovies()!!
                // Process the list of items
                // Update the ViewModel with the fetched items
            } catch (e: Exception) {
                // Handle the API call failure
            }


        return movieListModel
    }

    suspend fun     getSearchList(skip:String,limit:String): SearchListModel {
        // Make API call or fetch data from a local database
        // Return the list of items


        try {
            searchListModel = RestServiceBuilder.apiService?.getSearchList(range = "[$skip,$limit]")!!
            // Process the list of items
            // Update the ViewModel with the fetched items
        } catch (e: Exception) {
            // Handle the API call failure
        }


        return searchListModel
    }


}
