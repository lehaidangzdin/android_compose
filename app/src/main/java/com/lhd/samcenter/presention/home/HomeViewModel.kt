package com.lhd.samcenter.presention.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import com.lhd.samcenter.base.viewmodel.BaseViewModel
import com.lhd.samcenter.common.DataStoreManager
import com.lhd.samcenter.data.models.Product
import com.lhd.samcenter.data.repositories.FakeStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FakeStoreRepository,
    private val dataStore: DataStoreManager
) : BaseViewModel() {

    private var _isProgress = mutableStateOf(true)
    val isProgress: State<Boolean>
        get() = _isProgress

    private var _lsProduct = mutableStateListOf<Product>()
    val lsProduct: SnapshotStateList<Product>
        get() = _lsProduct

    init {
        fetchData()
    }

    override fun fetchData() {
        viewModelScope.launch(handler) {
            _isProgress.value = true
            val ls = repository.getAllProduct()
            _lsProduct.addAll(ls)
            _isProgress.value = false
        }
    }

    fun displayTxt(txt: String) {
        parentJob = viewModelScope.launch(handler) {
            dataStore.updateRefreshToken(txt)
        }
        registerJobFinish()
        Log.e("TAG1", "displayTxt: save token")
    }

    fun showTxt() {
        parentJob = viewModelScope.launch(handler) {
            dataStore.getRefreshToken().collectLatest {
                Log.e("TAG2", "showTxt: $it")
            }
        }
        registerJobFinish()
    }

}