package com.example.squaddev

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.welcome.WELCOME_ROUTE
import com.example.welcome.welcomeScreen
import login.loginScreen
import login.navigateToLogin

@Composable
fun MainApp(
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = WELCOME_ROUTE,
    ) {
        welcomeScreen(
            onLogin = { navController.navigateToLogin() },
            onSignUp = { navController.navigateToLogin() }
        )

        loginScreen(
            onLoggedIn = {},
            onNavigateUp = { navController.navigateUp() }
        )
    }
}