package com.example.android_project_template.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.android_project_template.di.util.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}