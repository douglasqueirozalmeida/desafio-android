package com.picpay.desafio.android.data.database.dao

import androidx.room.*
import com.picpay.desafio.android.data.model.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT u.* from user u where u.id=:key")
    suspend fun get(key: Int): User

    @Query("SELECT u.* from user u")
    suspend fun getAll(): List<User>?

    @Query("DELETE FROM user")
    suspend fun clear()
}