package com.esidem.mivatest.subject_details

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esidem.mivatest.utils.UIState
import com.esidem.mivatest.models.Chapter
import com.esidem.mivatest.usecases.GetChapterUseCase
import com.esidem.mivatest.models.LearningProgress
import com.esidem.mivatest.usecases.ResumeLearningUseCase
import com.esidem.mivatest.models.Subject
import com.esidem.mivatest.destinations.SubjectDetailsScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectDetailsScreenViewModel @Inject constructor(
    private val getChaptersUseCase: GetChapterUseCase,
    private val resumeLearningUseCase: ResumeLearningUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val navArgs = SubjectDetailsScreenDestination.argsFrom(savedStateHandle)
    val resumeLearningProgress = mutableStateOf<LearningProgress?>(null)

    val subject: Subject
        get() = navArgs.subject

    val uiState = mutableStateOf(UIState.IDLE)
    val chapters = mutableStateListOf<Chapter>()
    val searchQuery = mutableStateOf("")

    fun loadChapters() {
        uiState.value = UIState.LOADING
        viewModelScope.launch {
            getChaptersUseCase.getChapters("").collect{ result ->

                result.onSuccess {
                    chapters.clear()
                    chapters.addAll(it)
                }.onFailure {
                    uiState.value = UIState.ERROR
                }

            }

            resumeLearningProgress.value = resumeLearningUseCase.getSavedProgress()

            uiState.value = UIState.IDLE
        }
    }
}
