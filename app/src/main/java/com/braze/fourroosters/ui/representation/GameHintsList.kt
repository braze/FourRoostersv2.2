package com.braze.fourroosters.ui.representation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.braze.fourroosters.ui.components.HintCard
import kotlinx.coroutines.*


@Composable
fun GameHintList(listOfDescriptions: List<String>) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        state = listState,
    ) {
        itemsIndexed(
            items = listOfDescriptions
        ) { index, description ->
            HintCard(index + 1, description)
            // remember position of the description list
            LaunchedEffect("") {
                listState.scrollToItem(index, 0)
            }
        }
    }
}