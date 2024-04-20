package com.sarathexp.sampleapp.task.presentation.screen.home.tabs

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.sarathexp.sampleapp.task.data.model.User
import com.sarathexp.sampleapp.task.presentation.composable.UserCard

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Dashboard(modifier: Modifier = Modifier, usersPagingItems: LazyPagingItems<User>) {
    val context = LocalContext.current
    AnimatedContent(
        modifier = Modifier,
        targetState = usersPagingItems.itemCount > 0,
        transitionSpec = {
            fadeIn() +
                slideInVertically(
                    animationSpec = tween(500),
                    initialOffsetY = { fullHeight -> fullHeight }
                ) with fadeOut(animationSpec = tween(350))
        },
        label = "LoadState Refresh Animation"
    ) { state ->
        if (state) {
            LazyColumn(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(
                    count = usersPagingItems.itemCount,
                    key = usersPagingItems.itemKey { it.id },
                ) { index ->
                    val user = usersPagingItems[index]
                    if (user != null) {
                        UserCard(
                            user = user,
                            onClick = {
                                Toast.makeText(context, user.fullName, Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.padding(8.dp).fillMaxWidth()
                        )
                    }
                }
                item {
                    if (usersPagingItems.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        } else {
            CircularProgressIndicator(modifier = Modifier.size(34.dp))
        }
    }
}
