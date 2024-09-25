package com.example.edmer_technical_assignment.view.cardView

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.edmer_technical_assignment.model.Repository

@Composable
fun RepositoryCard(repo: Repository) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                openCustomTab(context, repo.html_url)
            },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = repo.name, style = MaterialTheme.typography.h6)
            Text(text = repo.language ?: "", style = MaterialTheme.typography.body1)
            Text(
                text = repo.description ?: "No description",
                style = MaterialTheme.typography.body2
            )
            Text(text = "Stars: ${repo.stargazers_count}", style = MaterialTheme.typography.body2)
        }
    }
}

private fun openCustomTab(context: Context, url: String) {
    val customTabsIntent = CustomTabsIntent.Builder().build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}