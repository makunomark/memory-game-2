package com.example.android_project_template.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_project_template.data.local.entities.Character
import com.example.android_project_template.data.models.Results
import com.example.android_project_template.data.repository.CharacterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MainViewModel
@Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    fun getRemoteCharacters() {
        viewModelScope.launch {
            val (_, results) = characterRepository.loadRemoteCharacters()
            if (!results.isNullOrEmpty()) {
                saveCharacters(results)
            }
        }
    }

    private fun saveCharacters(results: List<Results>) {
        viewModelScope.launch {
            for (result in results) {
                val character = Character(
                    result.id,
                    result.name,
                    result.url
                )

                characterRepository.saveCharacter(character)
            }
        }
    }
}
