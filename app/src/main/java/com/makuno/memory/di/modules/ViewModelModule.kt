package com.makuno.memory.di.modules

import androidx.lifecycle.ViewModel
import com.makuno.memory.di.annotation.ViewModelKey
import com.makuno.memory.ui.game.viewmodel.GameViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
 abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun bindMainViewModel(selectOrderViewModel: GameViewModel): ViewModel
}
