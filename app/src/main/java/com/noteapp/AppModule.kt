package com.noteapp

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
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
}