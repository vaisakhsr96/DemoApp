package com.example.demoapp.data.model.immerSphere.login.response

data class LoginResponse(
    val access_token: String,
    val channel_id: String,
    val `data`: Data,
    val message: String,
    val statusCode: Int
)