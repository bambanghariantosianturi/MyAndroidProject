package com.example.myandroidproject.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.source.local.entity.DataEntity
import com.example.myandroidproject.core.data.source.local.room.UserDao
import io.reactivex.Flowable

class LocalDataSource private constructor(private val userDao: UserDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(userDao: UserDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(userDao)
            }
    }

    /**
     * with livedata
     */
//    fun getAllData(): LiveData<List<DataEntity>> = userDao.getAllData()

    /**
     * with rx
     */
    fun getAllData(): Flowable<List<DataEntity>>  = userDao.getAllData()

    /**
     * with livedata
     */
//    fun getFavoriteData(): LiveData<List<DataEntity>> = userDao.

    /**
     * with rx
     */
//    fun getFavoriteData(): Flowable<List<DataEntity>> = userDao.updateFavoriteData()

    fun insertData(dataList: List<DataEntity>) = userDao.insertData(dataList)

    fun setFavoriteData(data: DataEntity, mState: Boolean) {
//        data.isfavorite = mState
        userDao.updateFavoriteData(data)
    }
}