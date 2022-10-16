package com.braze.fourroosters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.braze.fourroosters.ui.MainActivityViewModel
import com.braze.fourroosters.ui.components.DefaultSnackbar
import com.braze.fourroosters.ui.components.HowToPlayDialogWindow
import com.braze.fourroosters.ui.components.WinDialogWindow
import com.braze.fourroosters.ui.components.util.LostDialogWindow
import com.braze.fourroosters.ui.components.util.SnackbarController
import com.braze.fourroosters.ui.representation.*
import com.braze.fourroosters.ui.theme.FourRoostersTheme
import com.braze.fourroosters.ui.theme.gray
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {

    private val showDialog = mutableStateOf(false)
    private val snackbarController = SnackbarController(lifecycleScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val guessNumber = viewModel.guessNumber
        val boxBorderColors = viewModel.boxBorderColors
        val buttonState = viewModel.buttonState
        val listOfDescriptions = viewModel.listOfDescriptions
        val ssn = viewModel.secretNumber
        val winDialog = viewModel.winDialog
        val lostDialog = viewModel.lostDialog
        val howToPlayDialogWindow = viewModel.howToPlayDialogWindow

        setContent {
            FourRoostersTheme {
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

                Scaffold(
                    scaffoldState = scaffoldState,
                    snackbarHost = {
                        scaffoldState.snackbarHostState
                    },
                    // shout topBar
                    topBar = {
                        TopBar(ssn, showDialog)
                        if (showDialog.value) {
                            ShowDropDownMenu(
                                showDialog,
                                snackbarController,
                                scaffoldState,
                                guessNumber,
                                buttonState,
                                boxBorderColors,
                                listOfDescriptions,
                                ssn,
                                howToPlayDialogWindow
                            )
                        }
                    },
                    // main layout
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.BottomCenter),
                            verticalArrangement = Arrangement.Bottom,
                        ) {
                            // list of hints
                            Box(
                                Modifier
                                    .weight(1f) //This needs to scroll layout
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                GameHintList(listOfDescriptions)
                            }
                            WinDialogWindow(winDialog, buttonState, guessNumber, ssn)
                            LostDialogWindow(lostDialog, buttonState, guessNumber, ssn)
                            HowToPlayDialogWindow(howToPlayDialogWindow)
                            Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp))
                            Divider(
                                color = gray,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .width(1.dp),
                            )
                            Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp))
                            CodeLine(boxBorderColors, guessNumber, buttonState, ssn)
                            Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp))
                            InputNumbers(
                                ssn,
                                buttonState,
                                boxBorderColors,
                                guessNumber,
                                listOfDescriptions,
                                winDialog,
                                lostDialog
                            )
                            Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp))
                            DefaultSnackbar(
                                snackbarHostState = scaffoldState.snackbarHostState,
                                onDismiss = {
                                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                },
                                modifier = Modifier.align(Alignment.End)
                            )
                        }
                    }
                )
            }
        }
    }
}

