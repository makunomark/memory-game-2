package com.makuno.memory.di.modules

import com.makuno.memory.di.annotation.ActivityScope
import com.makuno.memory.ui.game.view.GameActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
 interface ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideMainActivity(): GameActivity
}