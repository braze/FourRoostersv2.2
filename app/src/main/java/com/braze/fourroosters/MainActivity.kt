package com.braze.fourroosters

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.braze.fourroosters.ui.BoxStateColor
import com.braze.fourroosters.ui.MainActivityViewModel
import com.braze.fourroosters.ui.components.DefaultSnackbar
import com.braze.fourroosters.ui.components.HowToPlayDialogWindow
import com.braze.fourroosters.ui.components.WinDialogWindow
import com.braze.fourroosters.ui.components.LostDialogWindow
import com.braze.fourroosters.ui.components.util.SnackbarController
import com.braze.fourroosters.ui.representation.*
import com.braze.fourroosters.ui.theme.FourRoostersTheme
import com.braze.fourroosters.ui.theme.gray
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val showDropDownDialog = mutableStateOf(false)
    private val snackBarController = SnackbarController(lifecycleScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        setContent {
            val guessNumber: Array<String> = viewModel.guessNumber.value
            val buttonState: Array<Boolean> = viewModel.buttonState.value
            val listOfDescriptions: List<String> = viewModel.listOfDescriptions.value
            val boxBorderColors: Array<BoxStateColor> = viewModel.boxBorderColors.value
            val winDialog = viewModel.winDialog.value
            val lostDialog = viewModel.lostDialog.value
            val howToPlayDialogWindow = viewModel.howToPlayDialogWindow.value

            FourRoostersTheme {
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

                Scaffold(
                    scaffoldState = scaffoldState,
                    snackbarHost = {
                        scaffoldState.snackbarHostState
                    },
                    // shout topBar
                    topBar = {
                        TopBar(showDialog = showDropDownDialog)
                        if (showDropDownDialog.value) {
                            ShowDropDownMenu(
                                showDialog = showDropDownDialog,
                                snackbarController = snackBarController,
                                scaffoldState = scaffoldState,
                                viewModel = viewModel
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
                            WinDialogWindow(
                                winDialog = winDialog,
                                viewModel = viewModel
                            )
                            LostDialogWindow(
                                lostDialog = lostDialog,
                                viewModel = viewModel
                            )
                            HowToPlayDialogWindow(
                                dialogOpen = howToPlayDialogWindow,
                                viewModel = viewModel)
                            Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp))
                            Divider(
                                color = gray,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .width(1.dp),
                            )
                            Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp))
                            CodeLine(
                                boxBorderColors = boxBorderColors,
                                guessNumber = guessNumber,
                                viewModel = viewModel
                            )
                            Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp))
                            InputNumbers(
                                buttonState = buttonState,
                                viewModel = viewModel
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

