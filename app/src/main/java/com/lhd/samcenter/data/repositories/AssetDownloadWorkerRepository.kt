package com.lhd.samcenter.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkQuery
import com.lhd.samcenter.service.MyWorkManager
import javax.inject.Inject

class AssetDownloadWorkerRepository @Inject constructor(private val context: Context) {
    private val workManager = WorkManager.getInstance(context)
    val data = Data.Builder()
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    private val assetDownloadRequest = OneTimeWorkRequestBuilder<MyWorkManager>()
        .setConstraints(constraints)
        .setInputData(data.build())
        .build()

    val downloadProgress: LiveData<Int> by lazy {
        workManager.getWorkInfosLiveData(WorkQuery.fromUniqueWorkNames("download_asset"))
            .map { it.singleOrNull()?.progress?.getInt("progress", 0) ?: 0 }
    }

    fun startAssetDownload(url: String) {
        data.apply {
            putString("image_url", url)
        }
        workManager.enqueueUniqueWork(
            "download_asset",
            ExistingWorkPolicy.REPLACE,
            assetDownloadRequest
        )
    }

}