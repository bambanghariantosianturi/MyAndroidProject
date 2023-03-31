package com.example.myandroidproject.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.source.local.entity.DataEntity
import com.example.myandroidproject.core.data.source.local.entity.GenreEntity
import com.example.myandroidproject.core.data.source.local.room.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val userDao: UserDao) {

//    companion object {
//        private var instance: LocalDataSource? = null
//
//        fun getInstance(userDao: UserDao): LocalDataSource =
//            instance ?: synchronized(this) {
//                instance ?: LocalDataSource(userDao)
//            }
//    }

    fun getAllData(): LiveData<List<DataEntity>> = userDao.getAllData()

//    fun getFavoriteData(): LiveData<List<DataEntity>> = userDao.

    fun insertData(dataList: List<DataEntity>) = userDao.insertData(dataList)

    fun setFavoriteData(data: DataEntity, mState: Boolean) {
//        data.isfavorite = mState
        userDao.updateFavoriteData(data)
    }

    fun getDetailMovie(): LiveData<DataEntity> = userDao.getDetailMovie()

    fun getGenreMovie(): LiveData<List<GenreEntity>> = userDao.getGenreMovie()

    fun insertGenreData(genreData: List<GenreEntity>) = userDao.insertGenreData(genreData)
}