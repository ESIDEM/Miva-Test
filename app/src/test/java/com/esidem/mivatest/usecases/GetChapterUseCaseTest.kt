package com.esidem.mivatest.usecases

import app.cash.turbine.test
import com.esidem.mivatest.fixture.ChatFixture
import com.esidem.mivatest.repository.ChapterRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


class GetChapterUseCaseTest {

    private lateinit var chapterUseCase: GetChapterUseCase
    private lateinit var chapterRepository: ChapterRepository


    @Before
    fun setup() {
        chapterRepository = mockk()
        chapterUseCase = GetChapterUseCase(chapterRepository)
    }

    @Test
    fun `getChapters() should return Flow of success when call is successful`() = runTest {

        coEvery { chapterRepository.getChapters("") } returns flowOf(Result.success(ChatFixture.chapter_1))

        chapterUseCase.getChapters("").test {
            val awaitItem = awaitItem()
            assertTrue(awaitItem.isSuccess)
            assertEquals(ChatFixture.chapter_1, awaitItem.getOrNull())
            awaitComplete()
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


}