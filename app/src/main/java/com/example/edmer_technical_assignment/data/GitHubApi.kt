package com.example.edmer_technical_assignment.model

import com.example.edmer_technical_assignment.data.GitHubUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): Response<GitHubUserResponse>

    @GET("users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): Response<GitHubUser>

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): Response<List<Repository>>
}
