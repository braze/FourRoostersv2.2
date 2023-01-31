package com.braze.fourroosters.ui

import androidx.compose.ui.graphics.Color
import com.braze.fourroosters.ui.theme.DarkMarine
import com.braze.fourroosters.ui.theme.darkGreen

enum class BoxStateColor (var value: Color){
    ACTIVE(darkGreen),
    PASSIVE(DarkMarine)
}