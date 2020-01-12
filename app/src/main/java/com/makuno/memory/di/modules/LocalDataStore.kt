package com.makuno.memory.di.modules

import android.app.Application
import androidx.room.Room
import com.makuno.memory.commons.Constants
import com.makuno.memory.data.local.ApplicationDatabase
import com.makuno.memory.data.local.dao.ProductDao
import com.makuno.memory.data.remote.api.ApplicationApi
import com.makuno.memory.data.repository.ProductRepository
import com.makuno.memory.data.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class LocalDataStore {

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
    fun provideScoreDao(database: ApplicationDatabase): ProductDao {
        return database.getScoreDao()
    }

    @Singleton
    @Provides
    fun provideCharactersRepository(
        applicationApi: ApplicationApi,
        productDao: ProductDao
    ): ProductRepository {
        return ProductRepositoryImpl(applicationApi, productDao)
    }
}