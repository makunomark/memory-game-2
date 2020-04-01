package com.makuno.memory.di

import android.app.Application
import com.makuno.memory.GameApplication
import com.makuno.memory.di.modules.*
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
 interface ApplicationComponent {

    fun inject(application: GameApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
