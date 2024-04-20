package com.sarathexp.sampleapp.task.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ScreenWrapper(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {

    // TODO: Add More Customization
    Scaffold(
        modifier = modifier,
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            content()
        }
    }
}
