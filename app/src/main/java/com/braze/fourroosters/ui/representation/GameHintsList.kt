package com.braze.fourroosters.ui.representation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.braze.fourroosters.ui.components.HintCard
import kotlinx.coroutines.*


@Composable
fun GameHintList(listOfDescriptions: MutableState<List<String>>) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        state = listState,
    ) {
        itemsIndexed(
            items = listOfDescriptions.value
        ) { index, description ->
            HintCard(index + 1, description)

            //remember list position
            coroutineScope.launch {
                listState.animateScrollToItem(
                    // TODO: check this out
                    listOfDescriptions.value.size - 1,
                    listState.firstVisibleItemScrollOffset
                )
            }
        }
    }
}