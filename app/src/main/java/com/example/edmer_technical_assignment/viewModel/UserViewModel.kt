package com.example.edmer_technical_assignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edmer_technical_assignment.model.GitHubApi
import com.example.edmer_technical_assignment.model.GitHubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val gitHubApi: GitHubApi
) : ViewModel() {
    private val _users = MutableLiveData<List<GitHubUser>>()
    val users: LiveData<List<GitHubUser>> get() = _users

    fun searchUsers(query: String) {
        viewModelScope.launch {
            try {
                val response = gitHubApi.searchUsers(query)
                if (response.isSuccessful) {
                    _users.value = response.body()?.items ?: emptyList()
                } else {
                    println("RESPONSE ERROR ${response.errorBody().toString()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
