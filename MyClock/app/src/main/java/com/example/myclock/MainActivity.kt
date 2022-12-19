package com.example.myclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myclock.ui.theme.MyClockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyClockTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    CardWithShape()
                }
            }
        }
    }
}

@Composable
fun CardWithShape() {
    //val paddingModifier = Modifier.padding(10.dp)
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        //modifier = paddingModifier
        modifier = Modifier.padding(10.dp)
    ) {
        Row {
            Text(text = "Round corner shape", Modifier.padding(10.dp))
            var onState by remember { mutableStateOf(true) }

            Button(
                onClick = { onState = !onState }
            ) {
                Text(if (onState) "Start" else "Pause")
            }
            Button(
                onClick = { /* handle click event */ }
            ) {
                Text("End")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyClockTheme {
        CardWithShape()
    }
}