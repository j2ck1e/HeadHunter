package com.jcdesign.headhunter.data.network

import com.jcdesign.headhunter.data.network.models.ResponseDto
import retrofit2.http.GET

interface ApiService {

    @GET("download?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun loadData(): ResponseDto
}