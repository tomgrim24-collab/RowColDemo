package com.example.rowcoldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RowColDemoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun RowColDemoScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Простой Column
        Text(
            text = "Column (вертикальное расположение):",
            fontSize = 16.sp,
            modifier = Modifier.padding(4.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Элемент 1", modifier = Modifier.padding(4.dp))
            Text("Элемент 2", modifier = Modifier.padding(4.dp))
            Text("Элемент 3", modifier = Modifier.padding(4.dp))
        }

        // 2. Простой Row
        Text(
            text = "Row (горизонтальное расположение):",
            fontSize = 16.sp,
            modifier = Modifier.padding(4.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightBlue)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("A", modifier = Modifier.padding(4.dp))
            Text("B", modifier = Modifier.padding(4.dp))
            Text("C", modifier = Modifier.padding(4.dp))
        }
    }
}