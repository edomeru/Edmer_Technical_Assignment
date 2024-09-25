package com.example.edmer_technical_assignment.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.edmer_technical_assignment.view.cardView.RepositoryCard
import com.example.edmer_technical_assignment.viewModel.UserRepositoryViewModel

@Composable
fun UserRepositoryScreen(userName: String, viewModel: UserRepositoryViewModel) {
    // Fetch user details and repositories
    LaunchedEffect(userName) {
        viewModel.fetchUserDetails(userName)
        viewModel.fetchUserRepos(userName)
    }

    val userDetails by viewModel.userDetails.collectAsState()
    val repos by viewModel.repos.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        userDetails?.let { user ->
            UserDetailSection(user)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Repositories", style = MaterialTheme.typography.h6)
        LazyColumn {
            items(repos) { repo ->
                RepositoryCard(repo)
            }
        }
    }
}