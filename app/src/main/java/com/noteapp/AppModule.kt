package com.noteapp

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.google.ai.client.generativeai.GenerativeModel
import com.noteapp.ui.AiViewModel
import com.noteapp.ui.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        val driver = AndroidSqliteDriver(NoteDatabase.Schema, get(), "note.db")
        NoteDatabase(driver)
    }
    single<NetworkMonitor> { AndroidNetworkMonitor(get()) }
    single { get<NoteDatabase>().noteEntityQueries }
    single<DeviceInfo> { AndroidDeviceInfo() }
    viewModel { NoteViewModel(get()) }
    single {
        GenerativeModel(
            modelName = "gemini-2.5-flash",
            apiKey = "AIzaSyAtv4Jsi8Rak4ZnrIX4i7a_AtNSkqqmhP8"
        )
    }
    viewModel { AiViewModel(get()) }
}
