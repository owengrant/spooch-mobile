package com.geoideas.spooch_mobile.http


data class ReturnedId(val id: String)
data class ReturnedToken(val token: String)
data class RegisteredUser(val username: String, val password: String, val password_confirm: String)
data class UserLogin(val username: String, val password: String)
data class EventDTO(val caption: String, val description: String, val location: List<Double>)