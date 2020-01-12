package com.makuno.memory.di.modules

import android.app.Application
import androidx.room.Room
import com.makuno.commons.Constants
import com.makuno.memory.data.local.ApplicationDatabase
import com.makuno.memory.data.local.dao.CharacterDao
import com.makuno.memory.data.remote.api.ApplicationApi
import com.makuno.memory.data.repository.CharacterRepository
import com.makuno.memory.data.repository.CharacterRepositoryImpl
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
    fun provideScoreDao(database: ApplicationDatabase): CharacterDao {
        return database.getScoreDao()
    }

    @Singleton
    @Provides
    fun provideCharactersRepository(
        applicationApi: ApplicationApi,
        characterDao: CharacterDao
    ): CharacterRepository {
        return CharacterRepositoryImpl(applicationApi, characterDao)
    }
}