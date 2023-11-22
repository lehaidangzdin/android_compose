package com.lhd.samcenter.service

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.net.URL

class MyWorkManager(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val imageUrl = inputData.getString("image_url")
        return try {
            val download = downloadImage(imageUrl ?: "")
            if (!download) Result.failure()

            Result.success()

        } catch (e: Exception) {
            Result.failure()
        }
    }

    private fun downloadImage(url: String): Boolean {
        val imageData = URL(url).readBytes()
        Log.e("MY WORKER MANAGER", "downloadImage: $imageData")
        if (imageData.isEmpty()) {
            return false
        }
        return true
    }
}