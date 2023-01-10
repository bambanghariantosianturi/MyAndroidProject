package com.example.myandroidproject.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.Data
import com.example.myandroidproject.core.domain.repository.IDataRepository

class DataInteractor (private val dataRepository: IDataRepository): DataUseCase {
    override fun getAllData(): LiveData<Resource<List<Data>>> {
        return dataRepository.getAllData()
    }

    override fun getFavoriteData(): LiveData<List<Data>> {
        return dataRepository.getFavoriteData()
    }

    override fun setFavoriteData(data: Data, state: Boolean) {
       return dataRepository.setFavoriteData(data, state)
    }
}