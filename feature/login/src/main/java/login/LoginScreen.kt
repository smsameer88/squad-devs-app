package login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.squaddev.core.view.R

@Composable
internal fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoggedIn: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    val state by loginViewModel.screenState.collectAsState()
    LaunchedEffect(key1 = state.loggedInUser) {
        state.loggedInUser?.let { _ -> onLoggedIn() }
    }
    LoginScreenContent(
        screenState = state,
        loginActions = loginViewModel,
        onNavigateUp = onNavigateUp
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun LoginScreenContent(
    screenState: LoginScreenState,
    loginActions: LoginActions,
    onNavigateUp: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = theme.AppTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = theme.AppTheme.colorScheme.onBackground
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.login_title),
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
        val verticalScrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = theme.AppTheme.size.medium)
                .verticalScroll(verticalScrollState),
            verticalArrangement = Arrangement.spacedBy(theme.AppTheme.size.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier.padding(theme.AppTheme.size.large)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = stringResource(id = R.string.cd_logo)
                )
            }
            Column(
                modifier = Modifier
                    .imePadding()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(theme.AppTheme.size.normal)
            ) {
                val passwordFocusRequester = FocusRequester()
                composabales.EmailInput(
                    modifier = Modifier.fillMaxWidth(),
                    email = screenState.email,
                    onEmailChanged = loginActions::updateEmail,
                    isInvalidEmailFormat = screenState.isWrongEmailFormat,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { passwordFocusRequester.requestFocus() }
                    )
                )
                composabales.PasswordInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequester),
                    password = screenState.password,
                    isInvalidPasswordFormat = screenState.isWrongPasswordFormat,
                    keyboardActions = KeyboardActions(
                        onDone = { loginActions.login() }
                    ),
                    onPasswordChanged = loginActions::updatePassword
                )
                if (screenState.wrongCredentials) {
                    Text(text = stringResource(id = R.string.error_invalid_credentials))
                }
                composabales.PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("loginButton"),
                    label = stringResource(R.string.login_title),
                    onClick = loginActions::login
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ErrorPrompt(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onDismiss: () -> Unit,
) {
    BasicAlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = AlertDialogDefaults.TonalElevation,
            color = theme.AppTheme.colorScheme.background,
            shape = RoundedCornerShape(theme.AppTheme.size.normal)
        ) {
            Column(
                modifier = Modifier.padding(theme.AppTheme.size.medium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(theme.AppTheme.size.large)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(R.string.label_error),
                        color = theme.AppTheme.colorScheme.onBackground,
                        style = theme.AppTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = errorMessage,
                        color = theme.AppTheme.colorScheme.onBackground,
                        style = theme.AppTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                    )
                }
                composabales.PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(R.string.label_ok),
                    onClick = onDismiss
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun PreviewLoginScreen() {
    theme.AppTheme {
        LoginScreenContent(
            screenState = LoginScreenState(),
            loginActions = EmptyLoginActions(),
            onNavigateUp = {}
        )
    }
}

@Composable
@PreviewLightDark
private fun PreviewErrorAlert() {
    theme.AppTheme {
        ErrorPrompt(
            errorMessage = "Something went wrong",
            onDismiss = {}
        )
    }
}

private class EmptyLoginActions : LoginActions {
    override fun updateEmail(newValue: String) {
        TODO("Not yet implemented")
    }

    override fun updatePassword(newValue: String) {
        TODO("Not yet implemented")
    }

    override fun login() {
        TODO("Not yet implemented")
    }
}