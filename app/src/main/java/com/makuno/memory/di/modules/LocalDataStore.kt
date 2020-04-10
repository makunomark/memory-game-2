package com.makuno.memory.di.modules

import android.app.Application
import androidx.room.Room
import com.makuno.memory.commons.Constants
import com.makuno.memory.data.local.ApplicationDatabase
import com.makuno.memory.data.local.dao.GameCardDao
import com.makuno.memory.data.local.dao.ScoreDao
import com.makuno.memory.data.remote.api.GameApplicationApi
import com.makuno.memory.data.repository.ProductRepository
import com.makuno.memory.data.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
 class LocalDataStore {

    @Singleton
    @Provides
    fun provideDatabase(context: Application): ApplicationDatabase {
        return Room.databaseBuilder(
            context,
            ApplicationDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }


    @Singleton
    @Provides
    fun provideProductDao(database: ApplicationDatabase): GameCardDao {
        return database.getProductDao()
    }

    @Singleton
    @Provides
    fun provideScoreDao(database: ApplicationDatabase): ScoreDao {
        return database.getScoreDao()
    }

    @Singleton
    @Provides
    fun provideCharactersRepository(
        gameApplicationApi: GameApplicationApi,
        gameCardDao: GameCardDao,
        scoreDao: ScoreDao
    ): ProductRepository {
        return ProductRepositoryImpl(gameApplicationApi, gameCardDao, scoreDao)
    }
}