package com.example.myandroidproject.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.Data

interface DataUseCase {

    fun getAllData(): LiveData<Resource<List<Data>>>

    fun getFavoriteData(): LiveData<List<Data>>

    fun setFavoriteData(data: Data, state: Boolean)
}