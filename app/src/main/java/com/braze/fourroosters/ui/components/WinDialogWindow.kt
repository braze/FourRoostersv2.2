package com.braze.fourroosters.ui.components


import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.braze.fourroosters.R
import com.braze.fourroosters.domain.UIEvent
import com.braze.fourroosters.ui.MainActivityViewModel
import com.braze.fourroosters.ui.theme.shadyWhite


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WinDialogWindow(
    winDialog: Boolean,
    viewModel: MainActivityViewModel
) {
    val primaryColor = Color(0xFF35898F)

    if (winDialog) {

        Dialog(
            onDismissRequest = {

                viewModel.onEvent(UIEvent.ShowLostDialog(false))
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false // experimental
            )
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(shadyWhite),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // medal icon
                    Icon(
                        painter = painterResource(id = R.drawable.ic_award),
                        contentDescription = "Medal icon",
                        tint = primaryColor,
                        modifier = Modifier.size(size = 150.dp)
                    )

                    Text(
                        text = "Congratulations!",
                        fontSize = 22.sp,
                        modifier = Modifier.padding(top = 26.dp),
                        fontFamily = FontFamily(
                            Font(
                                resId = R.font.roboto_black,
                                weight = FontWeight.Bold
                            )
                        )
                    )

                    Text(
                        text = "You have won the game.",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 20.dp),
                        fontFamily = FontFamily(
                            Font(
                                resId = R.font.roboto_regular,
                                weight = FontWeight.Normal
                            )
                        )
                    )
                }
                // ok button to dismiss the win dialog window
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Button(
                        onClick = {
                            viewModel.onEvent(UIEvent.EndOfTheGame)
                        },
                        modifier = Modifier.padding(top = 20.dp),
                        shape = RoundedCornerShape(percent = 25)
                    ) {
                        Text(
                            text = "OK",
                            fontFamily = FontFamily(
                                Font(
                                    resId = R.font.roboto_medium,
                                    weight = FontWeight.Medium
                                )
                            ),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}
