package com.braze.fourroosters.ui.representation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.braze.fourroosters.ui.MainActivityViewModel

@Composable
fun InputNumbers(
    buttonState: Array<Boolean>,
    viewModel: MainActivityViewModel
) {
    Column {
        //numbers row of numbers
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(4f))
            Button(
                onClick = {
                    viewModel.onNumberButtonClick(number = 0)
                },
                modifier = Modifier.padding(8.dp),
                enabled = buttonState[0]
            ) {
                Text(
                    text = "0",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.onNumberButtonClick(number = 1)
                },
                modifier = Modifier.padding(8.dp),
                enabled = buttonState[1]
            ) {
                Text(
                    text = "1",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.onNumberButtonClick(number = 2)
                },
                modifier = Modifier.padding(8.dp),
                enabled = buttonState[2]
            ) {
                Text(
                    text = "2",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.onNumberButtonClick(number = 3)
                },
                modifier = Modifier.padding(8.dp),
                enabled = buttonState[3]
            ) {
                Text(
                    text = "3",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.onNumberButtonClick(number = 4)
                },
                modifier = Modifier.padding(8.dp),
                enabled = buttonState[4]
            ) {
                Text(
                    text = "4",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.weight(4f))
        }
        //Second row of numbers
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(4f))
            Button(
                onClick = {
                    viewModel.onNumberButtonClick(number = 5)
                },
                modifier = Modifier.padding(8.dp),
                enabled = buttonState[5]
            ) {
                Text(
                    text = "5",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.onNumberButtonClick(number = 6)
                },
                modifier = Modifier.padding(8.dp),
                enabled = buttonState[6]
            ) {
                Text(
                    text = "6",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.onNumberButtonClick(number = 7)
                },
                modifier = Modifier.padding(8.dp),
                enabled = buttonState[7]
            ) {
                Text(
                    text = "7",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.onNumberButtonClick(number = 8)
                },
                modifier = Modifier.padding(8.dp),
                enabled = buttonState[8]
            ) {
                Text(
                    text = "8",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.onNumberButtonClick(number = 9)
                },
                modifier = Modifier.padding(8.dp),
                enabled = buttonState[9]
            ) {
                Text(
                    text = "9",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.weight(4f))
        }

        //functional buttons row
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.1f))

            //DELETE button
            Button(
                onClick = {
                    viewModel.onDeletePressed()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.5f),
                enabled = buttonState[10]

            ) {
                Text(
                    text = "Delete",
                    fontSize = 24.sp
                )
            }
            // OK button
            Button(
                onClick = {
                    viewModel.onOkButtonPressed()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.5f),
                enabled = buttonState[11]

            ) {
                Text(
                    text = "Ok",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}
