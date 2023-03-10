package com.example.myclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myclock.ui.theme.MyClockTheme
import com.example.myclock.component.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val intent = Intent(this, MyForegroundService::class.java)
        //this.startService(intent)

        setContent {
            MyClockTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val timers = remember { mutableStateListOf<Timer>() }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        CardWithShape(timers)
    }
    var showInput by remember { mutableStateOf(false) }
    var reset by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        showInput = CreateTemporary(reset)
        if(reset) {
            reset = false
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text = ""
        if(showInput) {
            text = OutlinedTextFieldComposable()
        }
        if(text > "") {
            try {
                val minutes: Int = text.toInt()
                timers.add(Timer(minutes, removeTimer = {timers.remove(it)}))
                reset = true
            } catch (e: Exception) {
                print(e)
            }
        }
    }
}

@Composable
fun CardWithShape(timers: MutableList<Timer>) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        modifier = Modifier.padding(10.dp)
    ) {
        Column() {
            timers.forEach {
                it.timer()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyClockTheme {
        //MyApp()
    }
}