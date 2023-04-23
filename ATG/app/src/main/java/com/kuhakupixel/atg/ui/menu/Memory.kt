package com.kuhakupixel.atg.ui.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuhakupixel.atg.ui.GlobalConf
import com.kuhakupixel.atg.ui.util.CheckboxWithText
import com.kuhakupixel.atg.ui.util.CreateTable

private var scanInputVal: MutableState<String> = mutableStateOf("")
private var scanAgainstValue: MutableState<Boolean> = mutableStateOf(true)

@Composable
fun MemoryMenu(globalConf: GlobalConf?) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        MatchesTable(
            modifier = Modifier
                .weight(0.6f)
                .padding(16.dp),
        )
        MatchesSetting(
            modifier = Modifier
                .weight(0.4f)
                .padding(10.dp)
        )
    }
}

@Composable
private fun MatchesSetting(modifier: Modifier = Modifier) {
    @Composable
    fun ScanInputField(scanValue: MutableState<String>, scanAgainstValue: MutableState<Boolean>) {
        Row() {
            CheckboxWithText(
                modifier = Modifier.weight(0.45f),
                checked = scanAgainstValue.value,
                onCheckedChange = { value ->
                    scanAgainstValue.value = value
                },
                text = "Against value",
            )
            TextField(
                modifier = Modifier.weight(0.65f),
                enabled = scanAgainstValue.value,
                value = scanValue.value,
                onValueChange = { value ->
                    scanValue.value = value
                },
                label = { Text(text = "Scan For") },
                placeholder = { Text(text = "value ...") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine=true,
            )
        }
    }

    Column(modifier = modifier) {
        ScanInputField(scanValue = scanInputVal, scanAgainstValue = scanAgainstValue)
    }
}

@Composable
private fun MatchesTable(modifier: Modifier = Modifier) {

    CreateTable(
        modifier = modifier,
        colNames = listOf("Address", "Previous Value"),
        colWeights = listOf(0.4f, 0.6f),
        itemCount = 0,
        minEmptyItemCount = 50,
        onRowClicked = { rowIndex: Int ->

        },
        drawCell = { rowIndex: Int, colIndex: Int, cellModifier: Modifier ->
            /*
            if (colIndex == 0) {
                Text(text = processList[rowIndex].GetPidStr(), modifier = cellModifier)
            }
            if (colIndex == 1) {
                Text(text = processList[rowIndex].GetName(), modifier = cellModifier)
            }
             */
        }
    )
}

@Composable
@Preview
fun MemoryMenuPreview() {
    MemoryMenu(null)
}