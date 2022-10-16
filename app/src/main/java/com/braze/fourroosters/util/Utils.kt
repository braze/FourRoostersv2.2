package com.braze.fourroosters.util

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun getBoxWidth(screenWidth: Dp): Int {
    val width = screenWidth.value.toInt().toPx()
    return ((width / 1.5 / 4) - 32).toInt()
}

fun getDpValue(screenWidth: Int): Dp {
    return screenWidth.toDp().dp
}