package com.example.myandroidproject.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myandroidproject.core.data.source.local.entity.DataEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllData(): LiveData<List<DataEntity>>

//    @Query("SELECT * FROM user where isFavorite = 1")
//    fun getFavoriteData(): LiveData<List<DataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: List<DataEntity>)

    @Update
    fun updateFavoriteData(data: DataEntity)
}