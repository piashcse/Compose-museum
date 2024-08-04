package com.piashcse.compose_museum.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateAndTimePicker(navController: NavController) {
    val dialogDateState = rememberMaterialDialogState()
    val dialogTimeState = rememberMaterialDialogState()
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    MaterialDialog(dialogState = dialogDateState, buttons = {
        positiveButton("Ok")
        negativeButton("Cancel")
    }) {
        datepicker { selectedDate ->
            // Do stuff with java.time.LocalDate object which is passed in
            date = "${selectedDate.dayOfMonth} - ${selectedDate.month} - ${selectedDate.year} "
        }
    }


    MaterialDialog(dialogState = dialogTimeState, buttons = {
        positiveButton("Ok")
        negativeButton("Cancel")
    }) {
        timepicker { selectedTime ->
            // Do stuff with java.time.LocalDate object which is passed in
            time = "${selectedTime.minute} : ${selectedTime.hour}"
        }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            dialogDateState.show()
        }) {
            Text(text = "DatePicker")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            dialogTimeState.show()
        }) {
            Text(text = "TimePicker")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(modifier = Modifier.fillMaxWidth(), text = "Selected Date: $date")
        Text(modifier = Modifier.fillMaxWidth(), text = "Selected Time: $time")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DateAndTimePickerPreview() {
    val navController = rememberNavController()
    DateAndTimePicker(navController)
}