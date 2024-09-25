package com.example.edmer_technical_assignment.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.edmer_technical_assignment.view.cardView.UserCard
import com.example.edmer_technical_assignment.viewModel.UserViewModel

@Composable
fun UserSearchScreen(viewModel: UserViewModel, navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val users by viewModel.users.observeAsState(emptyList())
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Search TextField
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = {
                Text(
                    "Search GitHub users",
                    color = Color.Black
                )
            }, // Set placeholder color to black
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(20.dp),
            singleLine = true
        )

        // Search Button
        Button(
            onClick = {
                viewModel.searchUsers(searchQuery)
                keyboardController?.hide()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black), // Set button color to black
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Search", color = Color.White) // Keep the text color white for contrast
        }

        Spacer(modifier = Modifier.height(8.dp))

        // User List
        LazyColumn {
            items(users) { user ->
                UserCard(user = user, onClick = {
                    navController.navigate("user/${user.login}")
                })
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
    }
}

