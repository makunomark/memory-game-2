package com.example.android_project_template.di.modules

import androidx.lifecycle.ViewModel
import com.example.android_project_template.di.annotation.ViewModelKey
import com.example.android_project_template.ui.main.viewmodel.MainViewModel
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
