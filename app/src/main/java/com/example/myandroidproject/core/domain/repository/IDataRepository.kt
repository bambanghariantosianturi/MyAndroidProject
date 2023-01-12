package com.example.myandroidproject.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.Data
import io.reactivex.Flowable

interface IDataRepository {

//    fun getAllData(): LiveData<Resource<List<Data>>>
//
//    fun getFavoriteData(): LiveData<List<Data>>

    /**
     * with rx
     */
    fun getAllData(): Flowable<Resource<List<Data>>>

    fun getFavoriteData(): Flowable<List<Data>>

    fun setFavoriteData(data: Data, state: Boolean)
}