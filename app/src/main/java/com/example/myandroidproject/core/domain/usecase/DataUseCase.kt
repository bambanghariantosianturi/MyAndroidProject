package com.example.myandroidproject.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.Data
import io.reactivex.Flowable

interface DataUseCase {

    fun getAllData(): Flowable<Resource<List<Data>>>

    fun getFavoriteData(): Flowable<List<Data>>

    fun setFavoriteData(data: Data, state: Boolean)
}