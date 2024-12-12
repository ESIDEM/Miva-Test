package com.esidem.mivatest.network

import com.esidem.mivatest.models.Chapter
import retrofit2.http.GET

interface MivaService {
    @GET("chapters")
    suspend fun getChapters() : List<Chapter>
}