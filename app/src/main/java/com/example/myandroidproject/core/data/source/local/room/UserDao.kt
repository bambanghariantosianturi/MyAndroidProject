package com.example.myandroidproject.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myandroidproject.core.data.source.local.entity.DataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllData(): Flow<List<DataEntity>>

//    @Query("SELECT * FROM user where isFavorite = 1")
//    fun getFavoriteData(): LiveData<List<DataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: List<DataEntity>)

    @Update
    fun updateFavoriteData(data: DataEntity)
}