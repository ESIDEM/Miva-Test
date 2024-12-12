package com.esidem.mivatest.repository

import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.offline.DownloadRequest

interface DownloadManager {
    @OptIn(UnstableApi::class)
    fun queueDownload(downloadRequest: DownloadRequest)
    fun removeDownload(contentId: String)
    fun pauseAllDownloads()
    fun resumeAllDownloads()
}
