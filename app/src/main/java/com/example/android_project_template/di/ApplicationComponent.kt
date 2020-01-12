package com.example.android_project_template.di

import android.app.Application
import com.example.android_project_template.TemplateApplication
import com.example.android_project_template.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        LocalDataStore::class,
        RemoteDataStore::class
    ]
)
internal interface ApplicationComponent {

    fun inject(application: TemplateApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
