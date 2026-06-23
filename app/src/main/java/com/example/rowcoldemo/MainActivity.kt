package com.example.rowcoldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
            text = "Column:",
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
            text = "Row:",
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

        // 3. Row с SpaceBetween
        Text(
            text = "Row с SpaceBetween:",
            fontSize = 16.sp,
            modifier = Modifier.padding(4.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGreen)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.size(40.dp).background(Color.Red))
            Box(modifier = Modifier.size(40.dp).background(Color.Green))
            Box(modifier = Modifier.size(40.dp).background(Color.Blue))
        }

        // 4. Вложенные Row и Column
        Text(
            text = "Вложенные Row + Column:",
            fontSize = 16.sp,
            modifier = Modifier.padding(4.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("1", modifier = Modifier.padding(8.dp).background(Color.Cyan))
                Text("2", modifier = Modifier.padding(8.dp).background(Color.Cyan))
                Text("3", modifier = Modifier.padding(8.dp).background(Color.Cyan))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("4", modifier = Modifier.padding(8.dp).background(Color.Magenta))
                Text("5", modifier = Modifier.padding(8.dp).background(Color.Magenta))
                Text("6", modifier = Modifier.padding(8.dp).background(Color.Magenta))
            }
        }

        // 5. Выравнивание в Column
        Text(
            text = "Выравнивание в Column:",
            fontSize = 16.sp,
            modifier = Modifier.padding(4.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.LightCoral)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("Верх", modifier = Modifier.background(Color.White).padding(4.dp))
            Text("Центр", modifier = Modifier.background(Color.White).padding(4.dp))
            Text("Низ", modifier = Modifier.background(Color.White).padding(4.dp))
        }

        // 6. Кнопки в Row
        Text(
            text = "Кнопки в Row:",
            fontSize = 16.sp,
            modifier = Modifier.padding(4.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp, 40.dp)
                    .background(Color(0xFF6200EE))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Кнопка 1", color = Color.White)
            }
            Box(
                modifier = Modifier
                    .size(80.dp, 40.dp)
                    .background(Color(0xFF03DAC5))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Кнопка 2")
            }
            Box(
                modifier = Modifier
                    .size(80.dp, 40.dp)
                    .background(Color(0xFFFF5722))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Кнопка 3", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowColDemoPreview() {
    MaterialTheme {
        RowColDemoScreen()
    }
}