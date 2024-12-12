package com.esidem.mivatest.usecases

import com.esidem.mivatest.models.Chapter
import com.esidem.mivatest.repository.ChapterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChapterUseCase@Inject constructor(
    private val chapterRepository: ChapterRepository
) {

    suspend fun getChapters(subject: String): Flow<Result<List<Chapter>>> {
        return chapterRepository.getChapters(subject)
    }
}