package com.aim.leantechtest.data.repository

import com.aim.leantechtest.data.api.ApiInstance
import com.aim.leantechtest.data.model.User
import io.reactivex.Single

class Repository(private val apiObj: ApiInstance) {

    fun getUsers(): Single<List<User>> {
        return apiObj.getUsers()
    }

}