package com.esidem.mivatest.di

import com.esidem.mivatest.repository.BookmarkManager
import com.esidem.mivatest.repository.BookmarkManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsBookmarkManager(
        bookmarkManager: BookmarkManagerImpl
    ) : BookmarkManager

}