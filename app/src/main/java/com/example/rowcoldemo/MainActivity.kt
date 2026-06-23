package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun TipCalcScreen(modifier: Modifier = Modifier) {
    var orderAmount by remember { mutableStateOf("") }
    var dishCount by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf(15f) }

    val discount = when {
        dishCount.isEmpty() -> 0
        dishCount.toIntOrNull() == null -> 0
        dishCount.toInt() in 1..2 -> 3
        dishCount.toInt() in 3..5 -> 5
        dishCount.toInt() in 6..10 -> 7
        dishCount.toInt() > 10 -> 10
        else -> 0
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Заголовок
        Text(
            text = "Калькулятор чаевых и скидок",
            fontSize = 24.sp,
            style = MaterialTheme.typography.titleLarge
        )

        // Поле для ввода суммы заказа
        OutlinedTextField(
            value = orderAmount,
            onValueChange = { orderAmount = it },
            label = { Text("Сумма заказа (₽)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Поле для ввода количества блюд
        OutlinedTextField(
            value = dishCount,
            onValueChange = { dishCount = it },
            label = { Text("Количество блюд") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Text(
            text = "Чаевые: ${tipPercentage.toInt()}%",
            fontSize = 18.sp
        )
        Slider(
            value = tipPercentage,
            onValueChange = { tipPercentage = it },
            valueRange = 0f..25f,
            steps = 25,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Скидка: $discount%",
            fontSize = 18.sp
        )
        Text(
            text = "Количество блюд: ${if (dishCount.isEmpty()) "0" else dishCount}",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

// Группа радиокнопок (неактивная для пользователя)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TipRadioButton(
                label = "1-2 блюда (3%)",
                selected = discount == 3,
                enabled = false
            )
            TipRadioButton(
                label = "3-5 блюд (5%)",
                selected = discount == 5,
                enabled = false
            )
            TipRadioButton(
                label = "6-10 блюд (7%)",
                selected = discount == 7,
                enabled = false
            )
            TipRadioButton(
                label = "Более 10 блюд (10%)",
                selected = discount == 10,
                enabled = false
            )
        }
    }
}