package com.example.myandroidproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.Data
import com.example.myandroidproject.core.domain.model.genremoviemodel.GenreItemModel
import com.example.myandroidproject.core.domain.model.listmoviesmodel.MovieItemModel
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

    override fun getAllData(page: Int, genreId: Int): LiveData<Resource<List<MovieItemModel>>> {
        return dataUseCase.getAllData(page, genreId)
    }

    override fun getGenreMovie(): LiveData<Resource<List<GenreItemModel>>> {
        return dataUseCase.getGenreMovie()
    }
}