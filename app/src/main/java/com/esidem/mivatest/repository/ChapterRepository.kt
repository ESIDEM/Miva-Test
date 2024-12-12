package com.esidem.mivatest.repository

import com.esidem.mivatest.models.Chapter
import kotlinx.coroutines.flow.Flow

interface ChapterRepository {
    suspend fun getChapters(subject: String): Flow<Result<List<Chapter>>>
}
