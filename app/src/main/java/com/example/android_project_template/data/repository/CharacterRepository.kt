package com.example.android_project_template.data.repository

import com.example.android_project_template.data.local.dao.CharacterDao
import com.example.android_project_template.data.local.entities.Character
import com.example.android_project_template.data.models.CharacterResponse
import com.example.android_project_template.data.remote.api.ApplicationApi
import javax.inject.Inject

internal interface CharacterRepository {

    suspend fun loadRemoteCharacters(): CharacterResponse

    suspend fun loadLocalCharacters(): List<Character>

    suspend fun saveCharacter(character: Character)
}

internal class CharacterRepositoryImpl
@Inject constructor(
    private val applicationApi: ApplicationApi,
    private val characterDao: CharacterDao
) : CharacterRepository {
    override suspend fun loadRemoteCharacters(): CharacterResponse {
        return applicationApi.getCharacters()
    }

    override suspend fun loadLocalCharacters(): List<Character> {
        return characterDao.getCharacters()
    }

    override suspend fun saveCharacter(character: Character) {
        characterDao.insert(character)
    }
}