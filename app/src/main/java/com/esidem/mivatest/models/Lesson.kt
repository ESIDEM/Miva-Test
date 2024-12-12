package com.esidem.mivatest.models

import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val title: String,
    val videoUrl: String
)
