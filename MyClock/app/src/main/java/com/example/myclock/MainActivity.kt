package com.example.myclock

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myclock.function.MyForegroundService
import com.example.myclock.ui.theme.MyClockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val intent = Intent(this, MyForegroundService::class.java)
        //this.startService(intent)

        setContent {
            MyClockTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    CardWithShape(this)
                }
            }
        }
    }
    fun myService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "testchannelname"
            val descriptionText = "testdescriptiontext"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("testchannelid", name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val intent = Intent(this, MyForegroundService::class.java)
        this.startService(intent)
    }
}

@Composable
fun CardWithShape(packageContext : MainActivity) {
    //val paddingModifier = Modifier.padding(10.dp)
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        //modifier = paddingModifier
        modifier = Modifier.padding(10.dp)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyClockTheme {
        //CardWithShape()
    }
}