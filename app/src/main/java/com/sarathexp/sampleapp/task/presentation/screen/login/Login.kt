package com.sarathexp.sampleapp.task.presentation.screen.login

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sarathexp.sampleapp.task.app.common.collectEvent
import com.sarathexp.sampleapp.task.app.common.collectState
import com.sarathexp.sampleapp.task.presentation.composable.ScreenWrapper
import com.sarathexp.sampleapp.task.presentation.screen.destinations.HomeDestination
import com.sarathexp.sampleapp.task.presentation.screen.login.interactor.LoginActionEvent
import com.sarathexp.sampleapp.task.presentation.screen.login.interactor.LoginUIEvent
import com.sarathexp.sampleapp.task.presentation.screen.login.interactor.LoginViewModel
import com.sarathexp.sampleapp.task.presentation.util.popAll

@Composable
@Destination
fun Login(navigator: DestinationsNavigator, viewModel: LoginViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val state by viewModel.collectState()
    val uiEvent by viewModel.collectEvent()

    ScreenWrapper {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp).imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Content(
                username = state.userName,
                userNameError = state.userNameError,
                password = state.password,
                passWordError = state.passWordError,
                isLoading = state.isLoading,
                onAction = viewModel::onActionEvent
            )
        }
    }

    LaunchedEffect(uiEvent) {
        uiEvent?.let {
            when (it) {
                is LoginUIEvent.LoginError ->
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                LoginUIEvent.LoginSuccess -> {
                    navigator.popAll { HomeDestination }
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.Content(
    username: String,
    userNameError: String?,
    password: String,
    passWordError: String?,
    isLoading: Boolean,
    onAction: (LoginActionEvent) -> Unit
) {

    val focusManager = LocalFocusManager.current

    Spacer(modifier = Modifier.weight(1f))

    Text(
        modifier = Modifier.padding(vertical = 8.dp).align(Alignment.Start),
        text = "Login",
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = username,
        onValueChange = { onAction(LoginActionEvent.UserNameChanged(it)) },
        supportingText = {
            userNameError?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        label = { Text("Username") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions { focusManager.moveFocus(FocusDirection.Next) }
    )
    val passwordVisibility = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
        value = password,
        onValueChange = { onAction(LoginActionEvent.PasswordChanged(it)) },
        supportingText = {
            passWordError?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        label = { Text("Password") },
        trailingIcon = {
            IconButton(
                onClick = { passwordVisibility.value = !passwordVisibility.value },
            ) {
                Icon(
                    imageVector =
                        if (passwordVisibility.value) Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        singleLine = true,
        visualTransformation =
            if (passwordVisibility.value) VisualTransformation.None
            else PasswordVisualTransformation(),
        keyboardOptions =
            KeyboardOptions(
                imeAction = ImeAction.Done,
            ),
        keyboardActions = KeyboardActions { focusManager.clearFocus() }
    )
    Spacer(modifier = Modifier.weight(1f))

    Button(
        modifier = Modifier.fillMaxWidth().height(45.dp),
        onClick = {
            if (!isLoading) {
                onAction(LoginActionEvent.Login)
            }
        },
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = isLoading,
                label = "Loading",
            ) { s ->
                if (s) {
                    CircularProgressIndicator(
                        modifier = Modifier.scale(0.65f),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeCap = StrokeCap.Round
                    )
                } else {
                    Text(
                        text = "Login",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}
