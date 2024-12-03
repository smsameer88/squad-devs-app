package com.example.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.squaddev.core.view.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WelcomeScreen(
    onLogin: () -> Unit,
    onSignUp: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = theme.AppTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = theme.AppTheme.colorScheme.onBackground,
                        style = theme.AppTheme.typography.titleNormal
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = theme.AppTheme.colorScheme.background,
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier.weight(.8f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.welcome_message),
                    color = theme.AppTheme.colorScheme.onBackground,
                    style = theme.AppTheme.typography.titleLarge
                )
            }
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.cd_logo)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(theme.AppTheme.size.large),
                verticalArrangement = Arrangement.spacedBy(
                    space = theme.AppTheme.size.small,
                    alignment = Alignment.Bottom
                )
            ) {
                composabales.PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.sign_up_title),
                    onClick = onSignUp
                )
                composabales.SecondaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.login_title),
                    onClick = onLogin
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun PreviewLoginScreen() {
    theme.AppTheme {
        WelcomeScreen(
            onLogin = {},
            onSignUp = {}
        )
    }
}