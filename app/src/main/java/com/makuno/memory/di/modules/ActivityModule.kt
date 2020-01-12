package com.makuno.memory.di.modules

import com.makuno.memory.di.annotation.ActivityScope
import com.makuno.memory.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal interface ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideMainActivity(): MainActivity
}