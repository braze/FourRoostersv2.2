package com.braze.fourroosters.model

import com.braze.fourroosters.ui.BoxStateColor

data class GameData(
    val listOfDescriptions: List<String>,
    val secretNumber: Array<String?>,
    val guessNumber: Array<String>,
    val boxBorderColors: Array<BoxStateColor>,
    val buttonState: Array<Boolean>
)
