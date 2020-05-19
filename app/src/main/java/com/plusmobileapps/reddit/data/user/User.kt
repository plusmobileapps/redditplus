package com.plusmobileapps.reddit.data.user

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class User(
    @PrimaryKey
    val id: String,
    val name: String,
    val karma: String
)

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUser(): LiveData<User>

    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

}