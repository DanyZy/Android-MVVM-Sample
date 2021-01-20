package com.aim.leantechtest.data.api

import com.aim.leantechtest.data.model.User
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiGithubUsersService: ApiService {

    override fun getUsers(): Single<List<User>> {
        return Rx2AndroidNetworking.get("https://api.github.com/users?since=0")
            .build()
            .getObjectListSingle(User::class.java)
    }

}