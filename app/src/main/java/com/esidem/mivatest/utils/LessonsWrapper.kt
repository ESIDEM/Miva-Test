package com.esidem.mivatest.utils

import com.esidem.mivatest.models.Lesson
import kotlinx.serialization.Serializable

@Serializable
data class LessonsWrapper(
    val lessons: List<Lesson>
) {
    operator fun get(index: Int): Lesson {
        return lessons[index]
    }
}
