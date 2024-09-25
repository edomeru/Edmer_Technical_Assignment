package com.example.edmer_technical_assignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edmer_technical_assignment.model.GitHubUser
import com.example.edmer_technical_assignment.model.Repository
import com.example.edmer_technical_assignment.model.GitHubApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRepositoryViewModel @Inject constructor(
    private val gitHubService: GitHubApi
) : ViewModel() {

    private val _userDetails = MutableStateFlow<GitHubUser?>(null)
    val userDetails: StateFlow<GitHubUser?> = _userDetails

    private val _repos = MutableStateFlow<List<Repository>>(emptyList())
    val repos: StateFlow<List<Repository>> = _repos

    fun fetchUserDetails(username: String) {
        viewModelScope.launch {
            val response = gitHubService.getUserDetails(username)
            if (response.isSuccessful) {
                _userDetails.value = response.body()
            }
        }
    }

    fun fetchUserRepos(username: String) {
        viewModelScope.launch {
            val response = gitHubService.getUserRepos(username)
            if (response.isSuccessful) {
                _repos.value = response.body() ?: emptyList()
            }
        }
    }
}