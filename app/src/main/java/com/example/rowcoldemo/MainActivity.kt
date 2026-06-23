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