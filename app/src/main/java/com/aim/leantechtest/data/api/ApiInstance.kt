package com.aim.leantechtest.data.api

class ApiInstance(private val apiService: ApiService) {

    fun getUsers() = apiService.getUsers()

}