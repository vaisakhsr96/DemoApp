package com.example.demoapp.data.model.immerSphere

data class SearchListModel(
    val data: List<Data>,
    val message: String,
    val statusCode: Int,
    val total: Int
)