package com.example.demoapp.data.model.MovieModel

data class MovieListModel(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)