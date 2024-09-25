package com.example.edmer_technical_assignment.model

data class GitHubUser(
    val login: String,
    val avatar_url: String,
    val id: Int,
    val name: String?,
    val followers: Int?,
    val following: Int?
)