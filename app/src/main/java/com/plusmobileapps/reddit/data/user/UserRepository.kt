package com.plusmobileapps.reddit.data.user

import androidx.lifecycle.LiveData
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class UserRepository(val client: HttpClient, val userDao: UserDao) {

    val user: LiveData<User> get() = userDao.getUser()

    init {
//        GlobalScope.launch(Dispatchers.IO) {
//            userDao.insertUser(User("1", "Andrew", "34"))
//        }
    }

    suspend fun loginUser(username: String, password: String) {

    }

}