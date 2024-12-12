package com.esidem.mivatest.repository

import com.esidem.mivatest.models.Bookmark
import com.esidem.mivatest.models.Lesson

interface BookmarkManager {
    fun getBookmarks(lesson: Lesson): List<Bookmark>
    fun addBookmark(lesson: Lesson, bookmark: Bookmark)
    fun removeBookmark(lesson: Lesson, bookmark: Bookmark)
}
