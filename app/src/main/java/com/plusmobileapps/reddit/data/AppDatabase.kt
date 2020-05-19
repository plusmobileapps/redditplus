package com.plusmobileapps.reddit.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plusmobileapps.reddit.data.user.User
import com.plusmobileapps.reddit.data.user.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}