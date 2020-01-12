package com.example.android_project_template.data.remote.api

import com.example.android_project_template.data.models.CharacterResponse
import retrofit2.http.GET

interface ApplicationApi {

    @GET("api/character/")
    suspend fun getCharacters(): CharacterResponse

}