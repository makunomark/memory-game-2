package com.makuno.memory.di.modules

import androidx.lifecycle.ViewModel
import com.makuno.memory.di.annotation.ViewModelKey
import com.makuno.memory.ui.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(selectOrderViewModel: MainViewModel): ViewModel
}
