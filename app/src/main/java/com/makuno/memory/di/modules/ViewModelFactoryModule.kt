package com.makuno.memory.di.modules

import androidx.lifecycle.ViewModelProvider
import com.makuno.memory.di.util.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
 abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}