package com.braze.fourroosters.model

import android.util.Log
import androidx.compose.runtime.MutableState
import com.braze.fourroosters.ui.representation.TAG


//@AndroidEntryPoint
class Game {

    fun generateSecretNumber(secretNumber: MutableState<Array<String?>>) {
        val array = arrayOfNulls<Int>(4)
        var index = 0
        while (true) {
            if (index > 3) {
                break
            } else {
                val digit = (0..9).random()
                if (!array.contains(digit)) {
                    array[index] = digit
                    index++
                }
            }
        }
        val sn: Array<String?> = arrayOfNulls(4)
        for (i in sn.indices) {
            sn[i] = array[i].toString()
        }
        secretNumber.value = sn
        Log.d(TAG, "generateSecretNumber: ${secretNumber.value.contentToString()}")
    }

    //compare secret and guess numbers, generate the hint
    fun checkNumber(
        secretNumber: MutableState<Array<String?>>,
        guessNumber: MutableState<Array<String>>,
        listOfDescriptions: MutableState<List<String>>,
        winDialog: MutableState<Boolean>,
        lostDialog: MutableState<Boolean>,
    ) {
        Log.d(TAG, "check- Number: ${guessNumber.value.contentToString()}")
        Log.d(TAG, "secret Number: ${secretNumber.value.contentToString()}")
        if (secretNumber.value.contentEquals(guessNumber.value)) {
            // WIN !!!! CONGRATULATIONS!!!
            getDescription(4, 0, listOfDescriptions, guessNumber)
            winDialog.value = true
        } else {
            var rooster = 0
            var chicken = 0
            for (i in secretNumber.value.indices) {
                for (j in guessNumber.value.indices) {
                    if (i == j && secretNumber.value[i] == guessNumber.value[j]) {
                        rooster++
                    } else if (i != j && secretNumber.value[i] == guessNumber.value[j]) {
                        chicken++
                    }
                }
            }
            getDescription(rooster, chicken, listOfDescriptions, guessNumber)
            winDialog.value = false
            Log.d(TAG, "checkNumber: ${listOfDescriptions.value.size}")
            if (listOfDescriptions.value.size == 10) {
                lostDialog.value = true
            }
        }
    }

    //generate hint description
    private fun getDescription(
        rooster: Int,
        chicken: Int,
        listOfDescriptions: MutableState<List<String>>,
        guessNumber: MutableState<Array<String>>,
    ) {
        val yourGuessNum = getStringNumber(guessNumber)
        var description = ""
        val roosterName: String = if (rooster > 1) "roosters" else "rooster"
        val chickenName: String = if (chicken > 1) "chickens" else "chicken"
        if (rooster == 4) {
            description = "Four roosters)"
        } else if (rooster > 0 && chicken > 0) {
            description = "$rooster $roosterName, $chicken $chickenName"
        } else if (rooster == 0 && chicken > 0) {
            description = "$chicken $chickenName"
        } else if (rooster > 0 && chicken == 0) {
            description = "$rooster $roosterName"
        } else if (rooster == 0 && chicken == 0) {
            description = "nothing"
        }
        listOfDescriptions.value = listOfDescriptions.value + listOf("$yourGuessNum $description")
    }


    private fun getStringNumber(guessNumber: MutableState<Array<String>>): String {
        return guessNumber.value[0] +
               guessNumber.value[1] +
               guessNumber.value[2] +
               guessNumber.value[3]
    }

}