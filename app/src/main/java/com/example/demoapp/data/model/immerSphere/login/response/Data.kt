package com.example.demoapp.data.model.immerSphere.login.response

data class Data(
    val email: String,
    val id: String,
    val is_active: Boolean,
    val status: Int,
    val userRoles: List<UserRole>,
    val userSetting: UserSetting,
    val user_name: String,
    val user_type: String
)