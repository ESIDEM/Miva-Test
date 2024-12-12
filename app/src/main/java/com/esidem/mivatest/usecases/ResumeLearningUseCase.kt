package com.esidem.mivatest.usecases

import com.esidem.mivatest.utils.LESSON_PROGRESS_PREF
import com.esidem.mivatest.models.LearningProgress
import com.esidem.mivatest.models.Lesson
import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import javax.inject.Inject

class ResumeLearningUseCase @Inject constructor(
    private val settings: Settings
) {

    fun saveLessonProgress(lesson: Lesson, timestamp: Long) {
        val jsonString = Json.encodeToString(
            LearningProgress(
                lesson = lesson,
                timestamp = timestamp
            )
        )

        settings[LESSON_PROGRESS_PREF] = jsonString
    }

    fun getSavedProgress(): LearningProgress? {
        if (settings.contains(LESSON_PROGRESS_PREF)) {
            val jsonString: String = settings[LESSON_PROGRESS_PREF] ?: return null

            return Json.decodeFromString(jsonString)
        }

        return null
    }

    fun clearProgress() {
        settings.remove(LESSON_PROGRESS_PREF)
    }
}