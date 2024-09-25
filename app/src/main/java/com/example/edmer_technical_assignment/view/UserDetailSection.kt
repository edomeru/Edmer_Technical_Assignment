package com.example.edmer_technical_assignment.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.edmer_technical_assignment.model.GitHubUser

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserDetailSection(user: GitHubUser) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(80.dp)) {
            GlideImage(
                model = user.avatar_url,
                contentDescription = user.login,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = user.login, style = MaterialTheme.typography.h5)
            Text(text = user.name ?: "No name", style = MaterialTheme.typography.body2)
            Text(text = "Followers: ${user.followers}", style = MaterialTheme.typography.body2)
            Text(text = "Following: ${user.following}", style = MaterialTheme.typography.body2)
        }
    }
}