package com.esidem.mivatest.models

import kotlinx.serialization.Serializable

@Serializable
data class LearningProgress(
    val lesson: Lesson,
    val timestamp: Long
)