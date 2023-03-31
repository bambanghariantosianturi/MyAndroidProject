package com.example.myandroidproject.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import com.example.myandroidproject.core.di.Injection
//import com.example.myandroidproject.di.AppScope
import javax.inject.Inject
import javax.inject.Provider

//@Suppress("UNCHECKED_CAST")
//@AppScope
//class MainViewModelFactory @Inject constructor(private val dataUseCase: DataUseCase) :
//    ViewModelProvider.NewInstanceFactory() {
//
////    companion object {
////        @Volatile
////        private var instance: MainViewModelFactory? = null
////
////        fun getInstance(context: Context): MainViewModelFactory =
////            instance ?: synchronized(this) {
////                instance ?: MainViewModelFactory(Injection.provideDataUseCase(context))
////            }
////    }
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return when {
//            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(dataUseCase) as T
//            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//        }
//    }
//}

//@AppScope
class MainViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return creator.get() as T
    }
}