package com.esidem.mivatest.di

import android.content.res.Resources
import com.esidem.mivatest.repository.BookmarkManager
import com.esidem.mivatest.usecases.BookmarkUseCase
import com.esidem.mivatest.repository.ChapterRepository
import com.esidem.mivatest.usecases.GetChapterUseCase
import com.esidem.mivatest.usecases.GetSubjectsUseCase
import com.esidem.mivatest.usecases.ResumeLearningUseCase
import com.russhwolf.settings.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetChapterUseCase(
        chapterRepository: ChapterRepository
    ): GetChapterUseCase = GetChapterUseCase(chapterRepository)

    @Singleton
    @Provides
    fun provideGetSubjectsUseCase(
        resources: Resources
    ): GetSubjectsUseCase = GetSubjectsUseCase(resources)

    @Singleton
    @Provides
    fun provideResumeLearningUseCase(
        settings: Settings
    ): ResumeLearningUseCase = ResumeLearningUseCase(settings)

    @Singleton
    @Provides
    fun provideGetBookmarksUseCase(
        bookmarkManager: BookmarkManager
    ): BookmarkUseCase = BookmarkUseCase(bookmarkManager)
}