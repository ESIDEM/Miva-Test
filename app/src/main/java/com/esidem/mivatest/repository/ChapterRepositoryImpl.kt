package com.esidem.mivatest.repository

import com.esidem.mivatest.models.Chapter
import com.esidem.mivatest.network.MivaService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChapterRepositoryImpl @Inject constructor(
    private val mivaService: MivaService
): ChapterRepository {
    override suspend fun getChapters(subject: String): Flow<Result<List<Chapter>>> = flow {
        try {
            val chapters = mivaService.getChapters()
            emit(Result.success(chapters))
        } catch (e: Exception) {
                emit(Result.failure(e))
        }
    }

}