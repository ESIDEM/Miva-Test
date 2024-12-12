package com.esidem.mivatest.usecases

import com.esidem.mivatest.models.Bookmark
import com.esidem.mivatest.repository.BookmarkManager
import com.esidem.mivatest.models.Lesson
import javax.inject.Inject

class BookmarkUseCase@Inject constructor(
    private val bookmarkManager: BookmarkManager
) {
    fun getBookmarks(lesson: Lesson): List<Bookmark> {
        return bookmarkManager.getBookmarks(lesson)
    }

    fun deleteBookmark(lesson: Lesson, bookmark: Bookmark) = bookmarkManager.removeBookmark(lesson, bookmark)

    fun addBookmark(lesson: Lesson, bookmark: Bookmark) = bookmarkManager.addBookmark(lesson, bookmark)
}