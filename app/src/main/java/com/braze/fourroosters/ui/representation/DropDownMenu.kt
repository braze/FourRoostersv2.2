package com.braze.fourroosters.ui.representation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.braze.fourroosters.domain.UIEvent
import com.braze.fourroosters.ui.MainActivityViewModel
import com.braze.fourroosters.ui.components.util.SnackbarController
import kotlinx.coroutines.launch

//https://www.androiddevelopersolutions.com/2021/09/android-jetpack-compose-toolbar.html
@ExperimentalMaterialApi
@Composable
fun ShowDropDownMenu(
    showDialog: MutableState<Boolean>,
    snackbarController: SnackbarController,
    scaffoldState: ScaffoldState,
    viewModel: MainActivityViewModel
) {
    var expanded by remember { mutableStateOf(true) }
    val items = listOf("New game", "How to play")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                showDialog.value = false
            },
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    androidx.compose.ui.graphics.Color.White
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDialog.value = false
                    when (index) {
                        0 -> {
                            snackbarController.getScope().launch {
                                snackbarController.showSnackbar(
                                    scaffoldState = scaffoldState,
                                    message = "New game started",
                                    actionLabel = "Hide"
                                )
                                viewModel.onEvent(UIEvent.NewGame)
                            }
                        }
                        1 -> {
                            viewModel.onEvent(UIEvent.ShowHowToPlayDialog(true))
                        }
                    }
                }) {
                    Text(text = s)
                }
            }
        }
    }
}
