package com.noteapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Nyalain Koin pas aplikasi pertama kali dibuka
        startKoin {
            androidContext(this@NoteApplication)
            modules(appModule)
        }
    }
}