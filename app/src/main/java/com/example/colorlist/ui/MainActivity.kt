package com.example.colorlist.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.colorlist.viewmodel.ColorViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ColorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ColorViewModel(application)

        setContent {
            Surface(color = MaterialTheme.colors.background) {
                Column(Modifier.padding(16.dp)) {
                    Button(onClick = {
                        val newColor = Color(color = "#${Integer.toHexString((Math.random() * 16777215)).toInt()}", time = System.currentTimeMillis())
                        viewModel.insertColor(newColor)
                    }) {
                        Text("Add Color")
                    }

                    Button(onClick = {
                        // Implement sync logic here
                    }) {
                        Text("Sync")
                    }

                    // Display color list here
                    val colors = viewModel.getAllColors().observeAsState(listOf())
                    Column {
                        colors.value.forEach { color ->
                            Text(color.color)
                        }
                    }
                }
            }
        }
    }
}