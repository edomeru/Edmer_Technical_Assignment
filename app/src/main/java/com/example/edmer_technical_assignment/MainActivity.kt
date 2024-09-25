package com.example.edmer_technical_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.edmer_technical_assignment.view.UserRepositoryScreen
import com.example.edmer_technical_assignment.view.UserSearchScreen
import com.example.edmer_technical_assignment.viewModel.UserRepositoryViewModel
import com.example.edmer_technical_assignment.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "search") {
        composable("search") {
            val userViewModel: UserViewModel = hiltViewModel()
            UserSearchScreen(viewModel = userViewModel, navController)
        }
        composable("user/{userName}") { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName")!!
            val userRepositoryViewModel: UserRepositoryViewModel = hiltViewModel()
            UserRepositoryScreen(userName = userName, viewModel = userRepositoryViewModel)
        }
    }
}


