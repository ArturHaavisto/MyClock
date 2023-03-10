package com.example.myclock.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.concurrent.thread


class Timer(minutes: Int, removeTimer: (timer: Timer) -> Unit) {
    private val milliseconds = minutes * 60 * 1000
    private val removeTimer = removeTimer
    
    @Composable
    fun timer() {
        var timeLeft by remember { mutableStateOf(milliseconds) }

        var running = false

        fun startTimer() {
            thread {
                while (timeLeft > 0 && running) {
                    Thread.sleep(10)
                    timeLeft -= 10
                }
            }
        }

        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 10.dp,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(50.dp),
            backgroundColor = Color.Blue
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(5.dp)
            ) {
                Text(
                    text = (timeLeft / 1000).toString(),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Button(
                    onClick = {
                        if (!running) {
                            startTimer()
                            running = true
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "start")
                }
                Button(onClick = {
                    running = false
                },
                    modifier = Modifier.weight(1f)) {
                    Text(text = "pause")
                }
                Button(onClick = { timeLeft += 60 * 1000 },
                    modifier = Modifier.weight(1f)) {
                    Text(text = "+1")
                }
                Button(onClick = {
                    running = false
                    thread {
                        Thread.sleep(20)
                        timeLeft = milliseconds
                    }
                },
                    modifier = Modifier.weight(1f)) {
                    Text(text = "reset")
                }
                Button(onClick = { removeTimer(this@Timer) },
                    modifier = Modifier.weight(1f)) {
                    Text(text = "X")
                }
            }
        }
    }


}