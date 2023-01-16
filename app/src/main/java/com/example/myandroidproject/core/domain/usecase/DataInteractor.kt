package com.example.myandroidproject.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.Data
import com.example.myandroidproject.core.domain.repository.IDataRepository
import kotlinx.coroutines.flow.Flow

class DataInteractor (private val dataRepository: IDataRepository): DataUseCase {
    override fun getAllData(): Flow<Resource<List<Data>>> {
        return dataRepository.getAllData()
    }

    override fun getFavoriteData(): Flow<List<Data>> {
        return dataRepository.getFavoriteData()
    }

    override fun setFavoriteData(data: Data, state: Boolean) {
       return dataRepository.setFavoriteData(data, state)
    }
}