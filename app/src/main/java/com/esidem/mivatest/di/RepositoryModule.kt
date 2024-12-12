package com.esidem.mivatest.di

import com.esidem.mivatest.repository.ChapterRepository
import com.esidem.mivatest.repository.ChapterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsChapterRepository(
        chapterRepository: ChapterRepositoryImpl
    ) : ChapterRepository

}