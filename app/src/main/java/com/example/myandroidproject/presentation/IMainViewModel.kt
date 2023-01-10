package com.example.myandroidproject.presentation

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.Data

interface IMainViewModel {

//    fun setName(name: String)

    fun getAllData(): LiveData<Resource<List<Data>>>
}