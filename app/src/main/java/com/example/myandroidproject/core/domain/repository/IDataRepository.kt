package com.example.myandroidproject.core.domain.repository

import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.Data
import kotlinx.coroutines.flow.Flow

interface IDataRepository {

    fun getAllData(): Flow<Resource<List<Data>>>

    fun getFavoriteData(): Flow<List<Data>>

    fun setFavoriteData(data: Data, state: Boolean)
}