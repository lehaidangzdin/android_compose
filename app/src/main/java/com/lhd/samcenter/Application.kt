package com.lhd.samcenter

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: Application() {

//    override fun onCreate() {
//        super.onCreate()
//
//        // Khởi chạy Intent để mở SubActivity khi ứng dụng bắt đầu
//        val intent = Intent(applicationContext, ImagesActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)  // Thêm cờ FLAG_ACTIVITY_NEW_TASK
//        applicationContext.startActivity(intent)
//    }
}