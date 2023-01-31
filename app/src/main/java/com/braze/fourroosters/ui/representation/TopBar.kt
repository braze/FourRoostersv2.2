package com.braze.fourroosters.ui.representation


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import com.braze.fourroosters.ui.MainActivityViewModel
import com.braze.fourroosters.ui.theme.gray
import com.braze.fourroosters.ui.theme.lightGray
import com.braze.fourroosters.ui.theme.orange

@Composable
fun TopBar(showDialog: MutableState<Boolean>) {
    TopAppBar(
        elevation = 4.dp,
        title = {
            Text("4Roosters")
        },
        backgroundColor = lightGray,
        actions = {
            IconButton(onClick = {
                showDialog.value = true
            }) {
                Icon(Icons.Outlined.Menu, null)
            }
        })
}
