package com.picpay.desafio.android.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.database.dao.UserDAO
import com.picpay.desafio.android.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class MyDataBase : RoomDatabase() {

    abstract val userDAO: UserDAO

    companion object {
        @Volatile
        private var INSTANCE: MyDataBase? = null

        fun getIntanceDatabase(context: Application): MyDataBase? {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MyDataBase::class.java,
                        "desafio-db"
                    )
                        .build()
                }
            }

            return INSTANCE
        }

        fun destroyerInstance() {
            INSTANCE = null
        }
    }
}