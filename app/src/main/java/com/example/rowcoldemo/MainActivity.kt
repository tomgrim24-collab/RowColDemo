package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
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
                    TipCalcScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TipCalcScreen(modifier: Modifier = Modifier) {
    var orderAmount by remember { mutableStateOf("") }
    var dishCount by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf(15f) }

    val amount = orderAmount.toDoubleOrNull() ?: 0.0
    val count = dishCount.toIntOrNull() ?: 0

    val discount = when {
        count in 1..2 -> 3
        count in 3..5 -> 5
        count in 6..10 -> 7
        count > 10 -> 10
        else -> 0
    }

    val discountAmount = amount * discount / 100
    val discountedAmount = amount - discountAmount
    val tipAmount = discountedAmount * tipPercentage / 100
    val totalAmount = discountedAmount + tipAmount

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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

        // Слайдер для чаевых
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

        // Радиокнопки для скидки
        Text(
            text = "Скидка: $discount%",
            fontSize = 18.sp
        )
        Text(
            text = "Количество блюд: $count",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

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

        // Результаты
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Итоговый расчёт:",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleMedium
                )
                Text("Сумма заказа: ${String.format("%.2f", amount)} ₽")
                Text("Скидка ($discount%): -${String.format("%.2f", discountAmount)} ₽")
                Text("Сумма со скидкой: ${String.format("%.2f", discountedAmount)} ₽")
                Text("Чаевые (${tipPercentage.toInt()}%): ${String.format("%.2f", tipAmount)} ₽")
                Divider()
                Text(
                    text = "Итого: ${String.format("%.2f", totalAmount)} ₽",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Composable
fun TipRadioButton(
    label: String,
    selected: Boolean,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null, // Неактивная кнопка
            enabled = enabled
        )
        Text(
            text = label,
            modifier = Modifier.padding(start = 8.dp),
            color = if (enabled) MaterialTheme.colorScheme.onSurface
            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalcPreview() {
    MaterialTheme {
        TipCalcScreen()
    }
}