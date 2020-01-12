package com.makuno.memory.data.repository

import com.makuno.memory.data.local.dao.CharacterDao
import com.makuno.memory.data.local.entities.Character
import com.makuno.memory.data.models.CharacterResponse
import com.makuno.memory.data.remote.api.ApplicationApi
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