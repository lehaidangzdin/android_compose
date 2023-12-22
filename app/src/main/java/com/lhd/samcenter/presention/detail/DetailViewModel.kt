package com.lhd.samcenter.presention.detail

import androidx.lifecycle.LiveData
import com.lhd.samcenter.base.viewmodel.BaseViewModel
import com.lhd.samcenter.common.DataStoreManager
import com.lhd.samcenter.data.repositories.AssetDownloadWorkerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: AssetDownloadWorkerRepository,
    private val dataStore: DataStoreManager
) : BaseViewModel() {

    val downloadProgress: LiveData<Int> = repository.downloadProgress

    fun downloadAsset(url: String) {
        repository.startAssetDownload(url)
    }
}