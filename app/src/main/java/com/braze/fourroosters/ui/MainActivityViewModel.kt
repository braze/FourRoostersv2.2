package com.braze.fourroosters.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.braze.fourroosters.domain.Game
import com.braze.fourroosters.domain.UIEvent
import com.braze.fourroosters.ui.BoxStateColor.*


class MainActivityViewModel : ViewModel() {

    private val game = Game()

    val guessNumber: MutableState<Array<String>> = mutableStateOf(game.guessNumber)
    val buttonState: MutableState<Array<Boolean>> = mutableStateOf(game.buttonState)
    val boxBorderColors: MutableState<Array<BoxStateColor>> = mutableStateOf(game.boxBorderColors)
    val listOfDescriptions: MutableState<List<String>> = mutableStateOf(game.listOfDescriptions)

    val winDialog: MutableState<Boolean> = mutableStateOf(false)
    val lostDialog: MutableState<Boolean> = mutableStateOf(false)
    val howToPlayDialogWindow: MutableState<Boolean> = mutableStateOf(false)

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.ShowHowToPlayDialog -> {
                howToPlayDialogWindow.value = event.wantToShow
            }
            is UIEvent.ShowWinDialog -> {
                winDialog.value = event.wantToShow
            }
            is UIEvent.ShowLostDialog -> {
                lostDialog.value = event.wantToShow
            }
            is UIEvent.NewGame -> {
                newGame()
            }
            is UIEvent.EndOfTheGame -> {
                onTheGameEnds()
                lostDialog.value = false
                winDialog.value = false
            }
        }
    }

    fun onUpdateBoxBorderColor(position: Int) {
        when (position) {
            0 -> {
                boxBorderColors.value[0] = ACTIVE
                boxBorderColors.value[1] = PASSIVE
                boxBorderColors.value[2] = PASSIVE
                boxBorderColors.value[3] = PASSIVE
            }
            1 -> {
                boxBorderColors.value[0] = PASSIVE
                boxBorderColors.value[1] = ACTIVE
                boxBorderColors.value[2] = PASSIVE
                boxBorderColors.value[3] = PASSIVE
            }
            2 -> {
                boxBorderColors.value[0] = PASSIVE
                boxBorderColors.value[1] = PASSIVE
                boxBorderColors.value[2] = ACTIVE
                boxBorderColors.value[3] = PASSIVE
            }
            3 -> {
                boxBorderColors.value[0] = PASSIVE
                boxBorderColors.value[1] = PASSIVE
                boxBorderColors.value[2] = PASSIVE
                boxBorderColors.value[3] = ACTIVE
            }
            else -> {
                boxBorderColors.value[0] = PASSIVE
                boxBorderColors.value[1] = PASSIVE
                boxBorderColors.value[2] = PASSIVE
                boxBorderColors.value[3] = PASSIVE
            }
        }
        onActivateDeleteButton(position)
    }

    fun onNumberButtonClick(number: Int) {
        val position = getHighLightedBox()
        if (position != -1) {
            guessNumber.value[position] = number.toString()
            onUpdateBoxBorderColor(-1)
            onDeactivateButtons()
            onActivateOkButton()
        }
    }

    fun onDeletePressed() {
        val position = getHighLightedBox()
        if (position != -1) {
            guessNumber.value[position] = "X"
            onUpdateBoxBorderColor(-1)
            onDeactivateButtons()
        }
    }

    fun onOkButtonPressed() {
        //generate new game for the first time.
        if (game.isSecretNumberNotReady()) {
            game.generateSecretNumber()
        }
        val (description, isWon) = game.checkNumber(guessNumber.value)
        listOfDescriptions.value = listOfDescriptions.value.plus(description.toString())
        winDialog.value = isWon as Boolean

        // check if player lost
        if (!winDialog.value) {
            if (listOfDescriptions.value.size == 10) {
                lostDialog.value = true
            }
        }
        resetViews()
    }

    private fun onDeactivateButtons() {
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

    private fun onActivateDeleteButton(pos: Int) {
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

    private fun onActivateOkButton() {
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

    private fun resetViews() {
        guessNumber.value = arrayOf("X", "X", "X", "X")
        buttonState.value = arrayOf(
            true, true, true, true, true, true, true, true, true, true,
            false, false
        )
        onUpdateBoxBorderColor(-1)
    }

    private fun getHighLightedBox(): Int {
        return boxBorderColors.value.indexOf(ACTIVE)
    }

    private fun newGame() {
        listOfDescriptions.value = listOf()
        game.generateSecretNumber()
        guessNumber.value = arrayOf("X", "X", "X", "X")
        boxBorderColors.value = arrayOf(PASSIVE, PASSIVE, PASSIVE, PASSIVE)
        buttonState.value = arrayOf(
            true, true, true, true, true, true, true, true, true,
            true, false, false
        )
    }

    private fun onTheGameEnds() {
        val list: Array<Boolean> = arrayOf(
            false, false, false, false, false, false, false, false, false, false, false, false
        )
        buttonState.value = list
        guessNumber.value = game.getSecretNumber()
    }
}
