package com.example.demoapp.data.model.immerSphere.login

data class LoginReq(var email:String,var password:String,var realm:String,var user_type:Array<String>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginReq

        if (!user_type.contentEquals(other.user_type)) return false

        return true
    }

    override fun hashCode(): Int {
        return user_type.contentHashCode()
    }
}
