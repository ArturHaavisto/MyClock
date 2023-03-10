package com.example.myclock.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun createPermanent() {

}

@Composable
fun CreateTemporary(reset: Boolean) : Boolean {
    var showInput by remember { mutableStateOf(false) }
    if(reset) {
        showInput = false
    }
    FloatingActionButton(
        onClick = { showInput = !showInput },
        modifier = Modifier.size(80.dp)
    ) {
        Icon(Icons.Filled.Add,"")
    }
    return showInput
}