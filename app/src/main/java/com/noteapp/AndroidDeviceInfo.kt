package com.noteapp

import android.os.Build

class AndroidDeviceInfo : DeviceInfo {
    override fun getDeviceModel(): String {
        return "${Build.MANUFACTURER} ${Build.MODEL}"
    }
}