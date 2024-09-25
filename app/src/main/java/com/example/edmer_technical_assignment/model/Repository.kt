package com.example.edmer_technical_assignment.model

data class Repository(
    val name: String,
    val language: String?,
    val stargazers_count: Int,
    val description: String?,
    val html_url: String
)