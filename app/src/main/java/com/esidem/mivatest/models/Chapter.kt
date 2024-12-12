package com.esidem.mivatest.models
import kotlinx.serialization.Serializable

@Serializable
data class Chapter(
    val title: String,
    val lessons: List<Lesson>
)
