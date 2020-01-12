package com.example.android_project_template.di.modules

import com.example.android_project_template.di.annotation.ActivityScope
import com.example.android_project_template.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal interface ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideMainActivity(): MainActivity
}