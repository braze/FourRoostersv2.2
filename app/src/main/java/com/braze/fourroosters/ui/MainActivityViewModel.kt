package com.braze.fourroosters.ui

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.braze.fourroosters.model.Game
import com.braze.fourroosters.model.GameData
import com.braze.fourroosters.ui.BoxStateColor.*
import dagger.hilt.android.lifecycle.HiltViewModel

//@HiltViewModel
class MainActivityViewModel : ViewModel() {

    private val playGame = Game()
    private val game = GameData(
        listOfDescriptions = listOf(),
        secretNumber = arrayOfNulls(4),
        guessNumber = arrayOf("X", "X", "X", "X"),
        boxBorderColors = arrayOf(PASSIVE, PASSIVE, PASSIVE, PASSIVE),
        buttonState = arrayOf(
            true, true, true, true, true, true, true, true, true,
            true, false, false
        )
    )

    val guessNumber: MutableState<Array<String>> = mutableStateOf(game.guessNumber)
    val buttonState: MutableState<Array<Boolean>> = mutableStateOf(game.buttonState)
    val boxBorderColors: MutableState<Array<BoxStateColor>> = mutableStateOf(game.boxBorderColors)
    val listOfDescriptions: MutableState<List<String>> = mutableStateOf(game.listOfDescriptions)
    val secretNumber: MutableState<Array<String?>> = mutableStateOf(game.secretNumber)
    val winDialog: MutableState<Boolean> = mutableStateOf(false)
    val lostDialog: MutableState<Boolean> = mutableStateOf(false)
    val howToPlayDialogWindow: MutableState<Boolean> = mutableStateOf(false)


    fun onUpdateBoxBorderColor(
        pos: Int,
        boxBorderColors: MutableState<Array<BoxStateColor>>,
        guessNumber: MutableState<Array<String>>,
        buttonState: MutableState<Array<Boolean>>,
        secretNumber: MutableState<Array<String?>>
    ) {
        if (!secretNumber.value.contentEquals(guessNumber.value)) {
            when (pos) {
                0 -> {
                    boxBorderColors.value = arrayOf(ACTIVE, PASSIVE, PASSIVE, PASSIVE)
                }
                1 -> {
                    boxBorderColors.value = arrayOf(PASSIVE, ACTIVE, PASSIVE, PASSIVE)
                }
                2 -> {
                    boxBorderColors.value = arrayOf(PASSIVE, PASSIVE, ACTIVE, PASSIVE)
                }
                3 -> {
                    boxBorderColors.value = arrayOf(PASSIVE, PASSIVE, PASSIVE, ACTIVE)
                }
                else -> {
                    boxBorderColors.value = arrayOf(PASSIVE, PASSIVE, PASSIVE, PASSIVE)
                }
            }
            onActivateDeleteButton(pos, guessNumber, buttonState)
        }
    }

    fun onNumberButtonClick(
        number: Int,
        boxBorderColors: MutableState<Array<BoxStateColor>>,
        guessNumber: MutableState<Array<String>>,
        buttonState: MutableState<Array<Boolean>>,
        secretNumber: MutableState<Array<String?>>
    ) {
        val pos = getHighLightedBox(boxBorderColors.value)
        if (pos != -1) {
            guessNumber.value[pos] = number.toString()
            guessNumber.value = arrayOf(
                guessNumber.value[0],
                guessNumber.value[1],
                guessNumber.value[2],
                guessNumber.value[3]
            )
            onUpdateBoxBorderColor(-1, boxBorderColors, guessNumber, buttonState, secretNumber)
            onDeactivateButtons(guessNumber, buttonState)
            onActivateOkButton(guessNumber, buttonState)
        }
    }

    fun onDeletePressed(
        boxBorderColors: MutableState<Array<BoxStateColor>>,
        guessNumber: MutableState<Array<String>>,
        buttonState: MutableState<Array<Boolean>>,
        secretNumber: MutableState<Array<String?>>
    ) {
        val pos = getHighLightedBox(boxBorderColors.value)
        if (pos != -1) {
            guessNumber.value[pos] = "X"
            guessNumber.value = arrayOf(
                guessNumber.value[0],
                guessNumber.value[1],
                guessNumber.value[2],
                guessNumber.value[3]
            )
            onUpdateBoxBorderColor(-1, boxBorderColors, guessNumber, buttonState, secretNumber)
            onDeactivateButtons(guessNumber, buttonState)
        }
    }

    fun onOkButtonPressed(
        ssn: MutableState<Array<String?>>,
        guessNumber: MutableState<Array<String>>,
        buttonState: MutableState<Array<Boolean>>,
        boxBorderColors: MutableState<Array<BoxStateColor>>,
        listOfDescriptions: MutableState<List<String>>,
        secretNumber: MutableState<Array<String?>>,
        winDialog: MutableState<Boolean>,
        lostDialog: MutableState<Boolean>
    ) {
        //generate new game for the first time.
        if (ssn.value.contains(null)) {
            playGame.generateSecretNumber(ssn)
        }
        playGame.checkNumber(ssn, guessNumber, listOfDescriptions, winDialog,lostDialog)
        resetViews(guessNumber, buttonState, boxBorderColors, secretNumber)
    }

    private fun getHighLightedBox(boxBorderColors: Array<BoxStateColor>): Int {
        return boxBorderColors.indexOf(ACTIVE)
    }

    private fun onDeactivateButtons(
        guessNumber: MutableState<Array<String>>,
        buttonState: MutableState<Array<Boolean>>
    ) {
        val list: Array<Boolean> = arrayOf(
            true, true, true, true, true, true, true, true, true, true, false, false
        )
        for (num in guessNumber.value) {
            if (num != "X") {
                val index = Integer.parseInt(num)
                list[index] = false
            }
        }
        buttonState.value = list
    }

    private fun onActivateDeleteButton(
        pos: Int,
        guessNumber: MutableState<Array<String>>,
        buttonState: MutableState<Array<Boolean>>
    ) {
        if (pos != -1 && guessNumber.value[pos] != "X") {
            val list: Array<Boolean> = arrayOf(
                buttonState.value[0],
                buttonState.value[1],
                buttonState.value[2],
                buttonState.value[3],
                buttonState.value[4],
                buttonState.value[5],
                buttonState.value[6],
                buttonState.value[7],
                buttonState.value[8],
                buttonState.value[9],
                true,
                buttonState.value[11]
            )
            buttonState.value = list
        } else {
            val list: Array<Boolean> = arrayOf(
                buttonState.value[0],
                buttonState.value[1],
                buttonState.value[2],
                buttonState.value[3],
                buttonState.value[4],
                buttonState.value[5],
                buttonState.value[6],
                buttonState.value[7],
                buttonState.value[8],
                buttonState.value[9],
                false,
                buttonState.value[11]
            )
            buttonState.value = list
        }
    }

    private fun onActivateOkButton(
        guessNumber: MutableState<Array<String>>,
        buttonState: MutableState<Array<Boolean>>
    ) {
        if (!guessNumber.value.contains("X")) {
            val list: Array<Boolean> = arrayOf(
                buttonState.value[0],
                buttonState.value[1],
                buttonState.value[2],
                buttonState.value[3],
                buttonState.value[4],
                buttonState.value[5],
                buttonState.value[6],
                buttonState.value[7],
                buttonState.value[8],
                buttonState.value[9],
                buttonState.value[10],
                true
            )
            buttonState.value = list
        } else {
            val list: Array<Boolean> = arrayOf(
                buttonState.value[0],
                buttonState.value[1],
                buttonState.value[2],
                buttonState.value[3],
                buttonState.value[4],
                buttonState.value[5],
                buttonState.value[6],
                buttonState.value[7],
                buttonState.value[8],
                buttonState.value[9],
                buttonState.value[10],
                false
            )
            buttonState.value = list
        }
    }

    private fun resetViews(
        guessNumber: MutableState<Array<String>>,
        buttonState: MutableState<Array<Boolean>>,
        boxBorderColors: MutableState<Array<BoxStateColor>>,
        secretNumber: MutableState<Array<String?>>
    ) {
        guessNumber.value = arrayOf("X", "X", "X", "X")
        buttonState.value = arrayOf(
            true, true, true, true, true, true, true, true, true, true,
            false, false
        )
        onUpdateBoxBorderColor(-1, boxBorderColors, guessNumber, buttonState, secretNumber)
    }

    fun newGame(
        guessNumber: MutableState<Array<String>>,
        buttonState: MutableState<Array<Boolean>>,
        boxBorderColors: MutableState<Array<BoxStateColor>>,
        listOfDescriptions: MutableState<List<String>>,
        secretNumber: MutableState<Array<String?>>
    ) {
        listOfDescriptions.value = listOf()
        playGame.generateSecretNumber(secretNumber)
        guessNumber.value = arrayOf("X", "X", "X", "X")
        boxBorderColors.value = arrayOf(PASSIVE, PASSIVE, PASSIVE, PASSIVE)
        buttonState.value = arrayOf(
            true, true, true, true, true, true, true, true, true,
            true, false, false
        )
    }

    fun onTheGameEnds(buttonState: MutableState<Array<Boolean>>,
                      guessNumber: MutableState<Array<String>>,
                      secretNumber: MutableState<Array<String?>>){
        val list: Array<Boolean> = arrayOf(
            false, false, false, false, false, false, false, false, false, false, false, false
        )
        buttonState.value = list
        val one = if (secretNumber.value[0] != null ) secretNumber.value[0].toString() else "X"
        val two = if (secretNumber.value[1] != null ) secretNumber.value[1].toString() else "X"
        val three = if (secretNumber.value[2] != null ) secretNumber.value[2].toString() else "X"
        val four = if (secretNumber.value[3] != null ) secretNumber.value[3].toString() else "X"
        guessNumber.value = arrayOf(one, two, three, four)
    }
}