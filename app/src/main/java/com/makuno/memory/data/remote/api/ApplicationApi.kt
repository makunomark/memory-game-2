package com.makuno.memory.data.remote.api

import com.makuno.memory.data.models.CharacterResponse
import retrofit2.http.GET

interface ApplicationApi {

    @GET("api/character/")
    suspend fun getCharacters(): CharacterResponse

}