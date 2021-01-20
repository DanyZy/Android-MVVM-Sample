package com.aim.leantechtest.data.api

import com.aim.leantechtest.data.model.User

import io.reactivex.Single

interface ApiService {

    fun getUsers(): Single<List<User>>

}