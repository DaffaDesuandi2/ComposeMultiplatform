package com.noteapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.noteapp.DeviceInfo
import org.koin.compose.koinInject

@Composable
fun ProfileScreen() {
    // Ambil instance DeviceInfo yang udah kita daftarin di AppModule tadi
    val deviceInfo: DeviceInfo = koinInject()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Informasi Perangkat", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        // Panggil fungsi yang tadi kita buat di AndroidDeviceInfo
        Text(text = deviceInfo.getDeviceModel())
    }
}