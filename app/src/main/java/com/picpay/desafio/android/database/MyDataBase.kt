package com.picpay.desafio.android.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.application.MyApplication
import com.picpay.desafio.android.database.dao.UserDAO
import com.picpay.desafio.android.model.User

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class MyDataBase : RoomDatabase() {

    abstract val userDAO: UserDAO

    companion object {
        @Volatile
        private var INSTANCE: MyDataBase? = null

        fun getIntanceDatabase(context: MyApplication): MyDataBase? {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MyDataBase::class.java,
                        "desafio-db"
                    )
//                        .addMigrations()
//                        .fallbackToDestructiveMigrationFrom()
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