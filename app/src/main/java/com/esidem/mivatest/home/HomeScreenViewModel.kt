package com.esidem.mivatest.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esidem.mivatest.usecases.GetSubjectsUseCase
import com.esidem.mivatest.models.Subject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getSubjectsUseCase: GetSubjectsUseCase,
) : ViewModel() {
    val searchQuery = mutableStateOf("")
    val subjects = mutableStateListOf<Subject>()

    fun loadSubjects() {
        viewModelScope.launch {
            subjects.clear()
            subjects.addAll(getSubjectsUseCase.getSubjects())
        }
    }
}