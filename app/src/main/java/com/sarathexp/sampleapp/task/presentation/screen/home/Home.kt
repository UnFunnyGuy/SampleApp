package com.sarathexp.sampleapp.task.presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sarathexp.sampleapp.task.app.common.collectEvent
import com.sarathexp.sampleapp.task.app.common.collectState
import com.sarathexp.sampleapp.task.presentation.composable.ScreenWrapper
import com.sarathexp.sampleapp.task.presentation.screen.home.interactor.HomeUIEvent
import com.sarathexp.sampleapp.task.presentation.screen.home.interactor.HomeViewModel
import com.sarathexp.sampleapp.task.presentation.screen.home.tabs.Dashboard
import com.sarathexp.sampleapp.task.presentation.screen.home.tabs.TabItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Destination
fun Home(navigator: DestinationsNavigator, viewModel: HomeViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val state by viewModel.collectState()
    val uiEvent by viewModel.collectEvent()

    val usersPagingItems = viewModel.pagedUsers.collectAsLazyPagingItems()
    val tabItems = remember { TabItem.entries.toList() }
    val pagerState = rememberPagerState(pageCount = { tabItems.size })

    ScreenWrapper {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TabRow(selectedTabIndex = pagerState.currentPage, divider = {}) {
                tabItems.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch { pagerState.animateScrollToPage(index) }
                        },
                        text = { Text(text = tabItem.title) },
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(0.7f)
                    )
                }
            }

            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth().weight(1f)) {
                index ->
                val selectedTab = remember(index) { tabItems[index] }

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        when (selectedTab) {
                            TabItem.DASHBOARD -> {
                                Dashboard(
                                    modifier = Modifier.fillMaxSize(),
                                    usersPagingItems = usersPagingItems
                                )
                            }
                            else -> Text(text = selectedTab.title, fontSize = 36.sp)
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(uiEvent) {
        uiEvent?.let {
            when (it) {
                is HomeUIEvent.Alert ->
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
