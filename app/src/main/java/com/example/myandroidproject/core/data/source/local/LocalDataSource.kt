package com.example.myandroidproject.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.source.local.entity.DataEntity
import com.example.myandroidproject.core.data.source.local.room.UserDao

class LocalDataSource private constructor(private val userDao: UserDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(userDao: UserDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(userDao)
            }
    }

    fun getAllData(): LiveData<List<DataEntity>> = userDao.getAllData()

//    fun getFavoriteData(): LiveData<List<DataEntity>> = userDao.

    fun insertData(dataList: List<DataEntity>) = userDao.insertData(dataList)

    fun setFavoriteData(data: DataEntity, mState: Boolean) {
//        data.isfavorite = mState
        userDao.updateFavoriteData(data)
    }
}