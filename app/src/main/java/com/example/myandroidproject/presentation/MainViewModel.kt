package com.example.myandroidproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.Data
import com.example.myandroidproject.core.domain.usecase.DataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataUseCase: DataUseCase) : ViewModel(), IMainViewModel {
//    private val _message = MutableLiveData<MessageEntity>()
//    val message: LiveData<MessageEntity>
//    get() = _message

//    fun setName(name: String) {
//        _message.value = messageUseCase.getMessage(name)
//    }

    override fun getAllData(): LiveData<Resource<List<Data>>> {
        return dataUseCase.getAllData() as LiveData<Resource<List<Data>>>
    }
}