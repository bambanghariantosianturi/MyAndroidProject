package com.example.myandroidproject.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myandroidproject.core.data.source.local.entity.DataEntity
import com.example.myandroidproject.core.data.source.local.entity.GenreEntity
import com.example.myandroidproject.core.data.source.local.entity.IntTypeConverter

@Database(entities = [DataEntity::class, GenreEntity::class], version = 1, exportSchema = false)
@TypeConverters(IntTypeConverter::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

//    companion object {
//        @Volatile
//        private var INSTANCE: UserDatabase? = null
//
//        fun getInstance(context: Context): UserDatabase =
//            INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    UserDatabase::class.java,
//                    "user.db"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//    }
}