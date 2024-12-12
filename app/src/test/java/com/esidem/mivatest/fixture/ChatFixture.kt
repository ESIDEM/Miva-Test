package com.esidem.mivatest.fixture

import com.esidem.mivatest.models.Chapter
import com.esidem.mivatest.models.Lesson

object ChatFixture {

    private val lesson_1 = Lesson("Lesson 1", "url_1")
    private val lesson_2 = Lesson("Lesson 2", "url_2")
    private val lesson_3 = Lesson("Lesson 3", "url_3")

    val chapter_1 = listOf( Chapter("Chapter 1", listOf(lesson_1, lesson_2, lesson_3)))
}