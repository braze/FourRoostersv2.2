package com.braze.fourroosters.domain

import android.util.Log
import com.braze.fourroosters.ui.BoxStateColor

class Game {

    private var secretNumber: Array<String?> = arrayOfNulls(4)
    val guessNumber = arrayOf("X", "X", "X", "X")
    val listOfDescriptions: List<String> = listOf()
    val boxBorderColors: Array<BoxStateColor> = arrayOf(
        BoxStateColor.PASSIVE,
        BoxStateColor.PASSIVE,
        BoxStateColor.PASSIVE,
        BoxStateColor.PASSIVE
    )
    val buttonState: Array<Boolean> = arrayOf(
        true, true, true, true, true, true, true, true, true,
        true, false, false
    )

    fun generateSecretNumber() {
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
        Log.d("TAG", "Secret Number: ${sn.contentToString()}")
        secretNumber = sn
    }

    //compare secret and guess numbers, generate the hint
    fun checkNumber(guessNumber: Array<String>): List<Any> {
        val description: String
        val winDialog: Boolean
        if (secretNumber.contentEquals(guessNumber)) {
            // WIN !!!! CONGRATULATIONS!!!
            description = getDescription(guessNumber,4, 0)
            winDialog = true
        } else {
            var rooster = 0
            var chicken = 0
            for (i in secretNumber.indices) {
                for (j in guessNumber.indices) {
                    if (i == j && secretNumber[i] == guessNumber[j]) {
                        rooster++
                    } else if (i != j && secretNumber[i] == guessNumber[j]) {
                        chicken++
                    }
                }
            }
            description = getDescription(guessNumber, rooster, chicken)
            winDialog = false
        }
        return listOf(description, winDialog)
    }

    //generate hint description
    private fun getDescription(
        guessNumber: Array<String>,
        rooster: Int,
        chicken: Int
    ): String {
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
        return "$yourGuessNum $description"
    }

    private fun getStringNumber(guessNumber: Array<String>): String {
        return guessNumber[0] +
                guessNumber[1] +
                guessNumber[2] +
                guessNumber[3]
    }

    fun isSecretNumberNotReady() : Boolean {
        return secretNumber.contains(null)
    }

    fun getSecretNumber(attempts: Int): Array<String> {
        if (attempts >= 10) {
                    val one =
            if (secretNumber[0] != null) secretNumber[0].toString() else "X"
        val two =
            if (secretNumber[1] != null) secretNumber[1].toString() else "X"
        val three =
            if (secretNumber[2] != null) secretNumber[2].toString() else "X"
        val four =
            if (secretNumber[3] != null) secretNumber[3].toString() else "X"
        return arrayOf(one, two, three, four)
        }
        return arrayOf("X", "X", "X", "X")
    }
}
