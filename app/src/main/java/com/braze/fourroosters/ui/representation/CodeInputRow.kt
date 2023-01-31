package com.braze.fourroosters.ui.representation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.braze.fourroosters.ui.BoxStateColor
import com.braze.fourroosters.ui.MainActivityViewModel
import com.braze.fourroosters.util.getBoxWidth
import com.braze.fourroosters.util.getDpValue


@Composable
fun CodeLine(
    boxBorderColors: Array<BoxStateColor>,
    guessNumber: Array<String>,
    viewModel: MainActivityViewModel
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val boxWidth = getBoxWidth(screenWidth).toFloat()
    val boxHeight = (boxWidth * 1.5).toFloat()
    Row(
        modifier = Modifier
            .padding(20.dp)
            .height(getDpValue(boxHeight.toInt() + 2))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {

        //box number 1
        Box(
            Modifier
                .size(getDpValue(boxWidth.toInt()), getDpValue(boxHeight.toInt()))
                .drawBehind {
                    drawRect(
                        brush = Brush.linearGradient(
                            listOf(
                                boxBorderColors[0].value,
                                boxBorderColors[0].value
                            )
                        ),
                        Offset(0f, 0f), Size(boxWidth, boxHeight),
                    )
                    drawRect(
                        brush = Brush.linearGradient(listOf(Color.White, Color.White)),
                        Offset(6f, 6f), Size(boxWidth - 12, boxHeight - 12),
                    )
                }
                .clickable {
                    viewModel.onUpdateBoxBorderColor(
                        0,
                    )
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = guessNumber[0],
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )
        }

        //box number two
        Box(
            Modifier
                .size(getDpValue(boxWidth.toInt()), getDpValue(boxHeight.toInt()))
                .drawBehind {
                    drawRect(
                        brush = Brush.linearGradient(
                            listOf(
                                boxBorderColors[1].value,
                                boxBorderColors[1].value
                            )
                        ),
                        Offset(0f, 0f), Size(boxWidth, boxHeight),
                    )
                    drawRect(
                        brush = Brush.linearGradient(listOf(Color.White, Color.White)),
                        Offset(6f, 6f), Size(boxWidth - 12, boxHeight - 12),
                    )
                }
                .clickable {
                    viewModel.onUpdateBoxBorderColor(
                        1,
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = guessNumber[1],
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )
        }

        //box number three
        Box(
            Modifier
                .size(getDpValue(boxWidth.toInt()), getDpValue(boxHeight.toInt()))
                .drawBehind {
                    drawRect(
                        brush = Brush.linearGradient(
                            listOf(
                                boxBorderColors[2].value,
                                boxBorderColors[2].value
                            )
                        ),
                        Offset(0f, 0f), Size(boxWidth, boxHeight),
                    )
                    drawRect(
                        brush = Brush.linearGradient(listOf(Color.White, Color.White)),
                        Offset(6f, 6f), Size(boxWidth - 12, boxHeight - 12),
                    )
                }
                .clickable {
                    viewModel.onUpdateBoxBorderColor(2)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = guessNumber[2],
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )
        }

        //box number four
        Box(
            Modifier
                .size(getDpValue(boxWidth.toInt()), getDpValue(boxHeight.toInt()))
                .drawBehind {
                    drawRect(
                        brush = Brush.linearGradient(
                            listOf(
                                boxBorderColors[3].value,
                                boxBorderColors[3].value
                            )
                        ),
                        Offset(0f, 0f), Size(boxWidth, boxHeight),
                    )
                    drawRect(
                        brush = Brush.linearGradient(listOf(Color.White, Color.White)),
                        Offset(6f, 6f), Size(boxWidth - 12, boxHeight - 12),
                    )
                }
                .clickable {
                    viewModel.onUpdateBoxBorderColor(
                        3,
                    )
                },
            contentAlignment = Alignment.Center,
            ) {
            Text(
                text = guessNumber[3],
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
            )
        }
    }
}
